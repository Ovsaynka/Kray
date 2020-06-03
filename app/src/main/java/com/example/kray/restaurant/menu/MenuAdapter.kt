package com.example.kray.restaurant.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import com.example.kray.data.Menu
import kotlinx.android.synthetic.main.menu_card.view.*

class MenuAdapter(private val mDescriptionList: ArrayList<Menu?>):
    RecyclerView.Adapter<MenuViewHolder>() {

    var listener: Listener? = null

    private val drawables = arrayListOf(
        R.drawable.main_dish_icon, +
        R.drawable.pizza_icon,
        R.drawable.dessert_icon,
        R.drawable.drinks_icon,
        R.drawable.salads_icon,
        R.drawable.sushi_icon
    )

    interface Listener {
        fun onItemClick(menu: Menu)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        mDescriptionList.sortWith(compareBy { it?.id })
        holder.bind(mDescriptionList[position]!!, drawables[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.menu_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mDescriptionList.size
    }

}