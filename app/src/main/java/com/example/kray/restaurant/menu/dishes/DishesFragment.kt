package com.example.kray.restaurant.menu.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import com.example.kray.data.Dishes
import com.example.kray.data.Menu
import kotlinx.android.synthetic.main.dish_fragment.*

private const val DISH_ID = "dishId"

class DishesFragment : MvpAppCompatFragment(), DishesView {

    companion object {
        fun newInstance(menu: Menu): Fragment {
            val args = Bundle()
            args.putSerializable(DISH_ID, menu)

            val fragment = DishesFragment()
            fragment.arguments = args

            return fragment
        }
    }

    private val mAdapter: DishesAdapter = DishesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dish_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu = requireArguments().getSerializable(DISH_ID) as Menu

        loadData(menu.dishes)
    }

     private fun loadData(dish: ArrayList<Dishes?>) {

        dishesRecyclerView.layoutManager = LinearLayoutManager(context)
        dishesRecyclerView.adapter = mAdapter
         mAdapter.setDishes(dish)
    }

}