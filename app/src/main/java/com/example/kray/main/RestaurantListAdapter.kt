package com.example.kray.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import com.example.kray.data.Restaurant
import java.util.*
import kotlin.collections.ArrayList

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantViewHolder>(), Filterable {

    private var mRestaurantList: ArrayList<Restaurant> = arrayListOf()
    private var mFilterRestaurantList: ArrayList<Restaurant> = arrayListOf()
    private var listener: Listener? = null

    init {
        mFilterRestaurantList = mRestaurantList
    }

    interface Listener {
        fun onItemClick(restaurant: Restaurant)
    }

    fun setItemClickListener(listener: Listener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(mFilterRestaurantList[position], listener)
    }

    override fun getItemCount(): Int = mFilterRestaurantList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rest_card, parent, false)

        return RestaurantViewHolder(view)
    }


    fun addItems(items: List<Restaurant>) {
        mFilterRestaurantList.addAll(items)
        notifyDataSetChanged()
    }

    private var onNothingFound: (() -> Unit)? = null
    fun search(s: String?, onNothingFound: (() -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().toLowerCase(Locale.getDefault())
                val filterResults = FilterResults()

                filterResults.values = if (charSearch.isEmpty()) {
                    mRestaurantList
                } else {
                    val resultList: ArrayList<Restaurant> = arrayListOf()
                    for (rest in mRestaurantList) {
                        if (rest.name?.toLowerCase(Locale.getDefault())
                                ?.contains(charSearch) == true
                        ) {
                            resultList.add(rest)
                        }
                    }
                    resultList
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mFilterRestaurantList =
                    results?.values as? ArrayList<Restaurant> ?: ArrayList<Restaurant>()
                notifyDataSetChanged()
            }
        }
    }

    fun getFilterAvg(progress: Int) {
        val resultList: ArrayList<Restaurant> = arrayListOf()
        for (rest in mRestaurantList) {
            if (rest.avgCheck!! >= progress)
                resultList.add(rest)

            mFilterRestaurantList = resultList as? ArrayList<Restaurant> ?: ArrayList()
            notifyDataSetChanged()
        }
    }
}