package com.example.kray.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kray.R
import com.example.kray.data.Comment
import com.example.kray.data.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_fragment.*

const val RESTAURANT_KEY = "restaurant"

class RestaurantFragment : Fragment(), RestaurantView {

    companion object {

        fun newInstance(restaurant: Restaurant): Fragment {
            val args = Bundle()
            args.putSerializable(RESTAURANT_KEY, restaurant)

            val fragment = RestaurantFragment()
            fragment.arguments = args

            return fragment
        }
    }

    //private val mAdapter: RestaurantAdapter = RestaurantAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant

        loadData(restaurant)

        feedbackRecyclerView.layoutManager = LinearLayoutManager(context)
        feedbackRecyclerView.adapter = RestaurantAdapter(restaurant.comments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_fragment, container, false)
    }

    override fun loadData(restaurant: Restaurant) {
        Picasso.get().load(restaurant.image).into(restImageView)

        nameRestTextView.text = restaurant.name.toString()
        descriptionTextView.text = restaurant.description.toString()
        restAddressTextView.text = restaurant.address.toString()
    }

    /*override fun setCommentList(comment: List<Comment>) {
       // mAdapter.addItems(comment)
    }*/
}