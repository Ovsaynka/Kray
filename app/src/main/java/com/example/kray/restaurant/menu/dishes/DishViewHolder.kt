package com.example.kray.restaurant.menu.dishes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.data.Dishes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dish_card.view.*

class DishViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(data: Dishes) {
        Picasso.get().load(data.image).into(itemView.dishImageView)

        itemView.titleTextView.text = data.name.toString()
        itemView.priceTextView.text = data.price.toString() + "$"
        itemView.descriptionTextView.text = data.description.toString()
    }
}