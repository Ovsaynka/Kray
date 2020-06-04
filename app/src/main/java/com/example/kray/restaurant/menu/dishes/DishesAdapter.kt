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

class DishesAdapter:  RecyclerView.Adapter<DishViewHolder>() {

    private val dishesList: MutableList<Dishes?> = mutableListOf()

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(dishesList[position]!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dish_card,
                parent,
                false
            )
        )
    }

    fun setDishes(dishes: List<Dishes?>){
        dishesList.addAll(dishes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dishesList.size
    }
}