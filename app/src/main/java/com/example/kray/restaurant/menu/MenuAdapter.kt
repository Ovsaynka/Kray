package com.example.kray.restaurant.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import com.example.kray.data.Menu

class MenuAdapter(private val mDescriptionList: Array<Menu?>):
    RecyclerView.Adapter<MenuAdapter.TypeMenuViewHolder>() {

    private var listener: Listener? = null

    private val drawables = arrayOf(
        R.drawable.main_dish_icon,+
        R.drawable.pizza_icon,
        R.drawable.dessert_icon,
        R.drawable.drinks_icon,
        R.drawable.salads_icon,
        R.drawable.sushi_icon
    )

    interface Listener{
        fun onItemClick(menu: Menu)
    }

    fun setItemClickListener(listener: Listener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: TypeMenuViewHolder, position: Int) {
        mDescriptionList.sortWith(compareBy {it?.id})
    holder.bind(mDescriptionList[position]!!, drawables[position], listener)
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
        return mDescriptionList.size
    }

    class TypeMenuViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val typeDrawable: ImageView = itemView.findViewById(R.id.typeImageView)
        private val typeText: TextView = itemView.findViewById(R.id.typeTextView)

        @SuppressLint("ClickableViewAccessibility")
         fun bind(menu: Menu, drawableId: Int, listener: Listener?) {
            typeDrawable.setImageDrawable(typeDrawable.context.getDrawable(drawableId))
            typeText.text = menu.description
             itemView.setOnClickListener { listener?.onItemClick(menu) }
        }
    }
}