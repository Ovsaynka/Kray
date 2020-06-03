package com.example.kray.restaurant.menu.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import com.example.kray.data.Dishes
import com.example.kray.data.Restaurant
import com.example.kray.restaurant.RESTAURANT_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dish_card.*

class DishesFragment : MvpAppCompatFragment(), DishesView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant

        //loadData(restaurant)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dish_fragment, container, false)
    }

    override fun loadData(dish: Dishes) {
        Picasso.get().load(dish.image).into(dishImageView)

        titleTextView.text = dish.name.toString()
        priceTextView.text = dish.price.toString()
        descriptionTextView.text = dish.description.toString()
    }

}