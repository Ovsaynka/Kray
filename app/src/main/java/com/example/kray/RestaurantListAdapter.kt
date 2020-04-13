package com.example.kray

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.models.Restaurant
import kotlinx.android.synthetic.main.rest_card.view.*

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private val mRestaurantList: MutableList<Restaurant> = mutableListOf()
    private var listener: Listener? = null

    interface Listener {
        fun onItemClick(restaurant: Restaurant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rest_card, parent, false)

        return ViewHolder(view)
    }

    fun setItemClickListener(listener: Listener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mRestaurantList[position], listener)
    }

    override fun getItemCount(): Int = mRestaurantList.count()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(restaurant: Restaurant, listener: Listener?) {
            itemView.nameRestTextView.text = restaurant.name
            itemView.addressTextView.text = restaurant.address
        }
    }

}