package com.example.kray.restaurant.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import com.example.kray.data.Menu
import com.example.kray.data.Restaurant
import com.example.kray.main.RestaurantListAdapter
import com.example.kray.restaurant.RESTAURANT_KEY
import com.example.kray.restaurant.RestaurantView
import com.example.kray.restaurant.menu.dishes.DishesFragment
import kotlinx.android.synthetic.main.menu_fragment.*


class MenuFragment: MvpAppCompatFragment(), MenuView, MenuAdapter.Listener {

    companion object {
        fun newInstance(restaurant: Restaurant): Fragment {
            val args = Bundle()
            args.putSerializable(RESTAURANT_KEY, restaurant)

            val fragment = MenuFragment()
            fragment.arguments = args

            return fragment
        }
    }
    private val mAdapter: MenuAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant

        loadData(restaurant)
        menuRecyclerView.setOnClickListener {
            Toast.makeText(view.context,"Кликайся!", Toast.LENGTH_SHORT).show()
        }

        mAdapter?.setItemClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onItemClick(menu: Menu) {
            val args = Bundle()
            args.putSerializable("id", menu.id)
            val fragment = DishesFragment()
            fragment.arguments = args
            val manager: FragmentManager = parentFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.dishFragment, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
    }

    override fun loadData(restaurant: Restaurant) {

        menuRecyclerView.layoutManager =  GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        menuRecyclerView.adapter = MenuAdapter(restaurant.menu)
    }
}