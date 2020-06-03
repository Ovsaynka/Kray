package com.example.kray.restaurant.menu.dishes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import com.example.kray.data.Dishes
import com.example.kray.data.Menu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dish_card.view.*

class DishesAdapter:  RecyclerView.Adapter<DishesAdapter.TypeMenuViewHolder>() {

    private val dishesList: MutableList<Dishes> = mutableListOf()

    override fun onBindViewHolder(holder: TypeMenuViewHolder, position: Int) {
        holder.bind(dishesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeMenuViewHolder {
        return TypeMenuViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.menu_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }

    class TypeMenuViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(data: Dishes) {
            Picasso.get().load(data.image).into(itemView.dishImageView)

            itemView.titleTextView.text = data.name.toString()
            itemView.priceTextView.text = data.price.toString()
            itemView.descriptionTextView.text = data.description.toString()
        }
    }
}