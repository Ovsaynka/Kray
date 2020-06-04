package com.example.kray.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.data.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rest_card.view.*

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(restaurant: Restaurant, listener: RestaurantListAdapter.Listener?) {
        Picasso.get().load(restaurant.image).into(itemView.restaurantImageView)

        itemView.nameRestTextView.text = restaurant.name
        itemView.addressTextView.text = restaurant.address
        itemView.rateTextView.text = restaurant.stars.toString()
        itemView.setOnClickListener {
            listener?.onItemClick(restaurant)
        }
    }
}