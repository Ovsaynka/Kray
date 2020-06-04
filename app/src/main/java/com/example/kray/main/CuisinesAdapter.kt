package com.example.kray.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import kotlinx.android.synthetic.main.cuisines_item.view.*

class CuisinesAdapter : RecyclerView.Adapter<CuisinesAdapter.CuisinesViewHolder>() {

    private val mCuisinesList = arrayListOf(
        "Turkish",
        "Chinese",
        "Ukrainian",
        "Italian",
        "Japanese"
    )

    override fun onBindViewHolder(holder: CuisinesViewHolder, position: Int) {
        holder.bind(mCuisinesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisinesViewHolder {
        return CuisinesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cuisines_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mCuisinesList.count()

    class CuisinesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: String) {
            itemView.cuisinesListTextView.text = data
        }
    }
}