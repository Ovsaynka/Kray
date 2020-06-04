package com.example.kray.restaurant.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kray.R
import com.example.kray.data.Restaurant
import com.example.kray.restaurant.RestaurantView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.address_fragment.*


const val RESTAURANT_KEY = "restaurant"

class AddressFragment : Fragment(), RestaurantView, OnMapReadyCallback {

    private var mMapView: MapView? = null
    private var mView: View? = null

    private var latitude: Double? = null
    private var longtitude: Double? = null

    private var name: String? = null

    companion object {
        fun newInstance(restaurant: Restaurant): Fragment {
            val args = Bundle()
            args.putSerializable(RESTAURANT_KEY, restaurant)

            val fragment = AddressFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant

        loadData(restaurant)

        mMapView =
            mView!!.findViewById<View>(R.id.mapView) as MapView
        if (mMapView != null) {
            mMapView!!.onCreate(null)
            mMapView!!.onResume()
            mMapView!!.getMapAsync(this)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.address_fragment, container, false)
        return mView
    }

    override fun loadData(restaurant: Restaurant) {

        textAddress.text = restaurant.address.toString()
        textTelephone.text = restaurant.phone.toString()

        latitude = restaurant.latitude
        longtitude = restaurant.longtitude
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MapsInitializer.initialize(requireContext())
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        googleMap.addMarker(
            latitude?.let { longtitude?.let { it1 -> LatLng(it, it1) } }?.let {
                MarkerOptions()
                    .position(it).title(name)
                    .snippet(name)
            }
        )
        val cameraPosition =
            CameraPosition.Builder()
                .target(longtitude?.let { latitude?.let { it1 -> LatLng(it1, it) } }).zoom(16.0f)
                .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        googleMap.moveCamera(cameraUpdate)

    }

}



