package com.example.kray.restaurant.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import com.example.kray.data.Menu
import com.example.kray.data.Restaurant
import com.example.kray.restaurant.RESTAURANT_KEY
import com.example.kray.restaurant.RestaurantRouter
import kotlinx.android.synthetic.main.menu_fragment.*


class MenuFragment : MvpAppCompatFragment() {

    companion object {
        fun newInstance(restaurant: Restaurant): Fragment {
            val args = Bundle()
            args.putSerializable(RESTAURANT_KEY, restaurant)

            val fragment = MenuFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private var mAdapter: MenuAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant
        mAdapter = MenuAdapter(restaurant.menu)

        mAdapter!!.listener = listener

        menuRecyclerView.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        menuRecyclerView.adapter = mAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    private val listener = object : MenuAdapter.Listener {
        override fun onItemClick(menu: Menu) {
            val router = activity as? RestaurantRouter
            router?.navigateToDishes(menu)
        }

    }
}