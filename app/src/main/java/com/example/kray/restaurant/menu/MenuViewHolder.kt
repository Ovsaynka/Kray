package com.example.kray.restaurant.menu

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.data.Menu
import kotlinx.android.synthetic.main.menu_card.view.*

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(menu: Menu, drawableId: Int, listener: MenuAdapter.Listener?) {

        itemView.typeImageView.setImageDrawable(itemView.context.getDrawable(drawableId))
        itemView.typeTextView.text = menu.description
        itemView.cardview.setOnClickListener {  listener!!.onItemClick(menu) }
    }
}