package com.example.kray.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kray.R
import com.example.kray.SessionManager
import com.example.kray.data.Restaurant
import kotlinx.android.synthetic.main.main_page_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.get

const val RESTAURANT_KEY = "restaurant"

class MainPageFragment : MvpAppCompatFragment(),
    MainPageView, RestaurantListAdapter.Listener {

    lateinit var session: SessionManager

    @InjectPresenter
    lateinit var mPresenter: RestaurantPresenter

    lateinit var navController: NavController

    @ProvidePresenter
    fun provideRestaurantPresenter() = get<RestaurantPresenter>()

    private val mAdapter: RestaurantListAdapter =
        RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = SessionManager(requireContext())
        return inflater.inflate(R.layout.main_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        restaurantRecyclerView.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        restaurantRecyclerView.adapter = mAdapter
        mPresenter.fetchRestaurant()


        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                search(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        avgCheckSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    mAdapter.getFilterAvg(seekBar!!.progress)
                    Toast.makeText(view.context,"Avg check "+ seekBar.progress,Toast.LENGTH_SHORT).show()
                }
            })
        mAdapter.setItemClickListener(this)

        session.checkLogin()



        if(session.isLoggedIn()) {
            val user: Map<String, String>

            user = session.getUserDetails()

            val userName: String = user.get(SessionManager.KEY_USERNAME)!!

            userNameTextView.text = userName
        } else
        {
            userNameTextView.text = ""
        }

        logout.setOnClickListener {
            if(session.isLoggedIn()) {
                session.LogoutUser()
                findNavController().navigate(R.id.action_mainPageFragment_to_startPageFragment)
            }
        }
    }

    private fun search(s: String?) {
        mAdapter.search(s) {
            Toast.makeText(view?.context, "Nothing Found", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onItemClick(restaurant: Restaurant) {
        findNavController().navigate(
            R.id.action_mainPageFragment_to_restaurantActivity,
            bundleOf(RESTAURANT_KEY to restaurant)
        )
    }

    override fun setRestaurantList(restaurants: List<Restaurant>) {
        mAdapter.addItems(restaurants)
    }

}