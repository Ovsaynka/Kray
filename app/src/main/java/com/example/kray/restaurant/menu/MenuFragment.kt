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
import com.example.kray.restaurant.menu.dishes.DishesFragment
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
            val fragment = DishesFragment.newInstance(menu)
            if (!isAdded) return
            fragment.childFragmentManager.beginTransaction()
                .replace(R.id.dishFragment, fragment)
                .addToBackStack(null)
                .commit()
           /* val args = Bundle()
            args.putSerializable("id", menu.id)
            val fragment = DishesFragment()
            fragment.arguments = args
            childFragmentManager.beginTransaction()
            .replace(R.id.dishFragment, fragment)
            .addToBackStack(null)
            .commit()*/
        }

    }
}