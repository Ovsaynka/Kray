package com.example.kray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.main_page_fragment.*

class MainPageFragment : MvpAppCompatFragment() {

    @InjectPresenter
    lateinit var mPresenter: RestaurantPresenter

    private var mAdapter: RestaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchBar =
            view.findViewById(R.id.searchView) as SearchView

        restaurantRecyclerView.layoutManager = LinearLayoutManager(context)
        restaurantRecyclerView.adapter = mAdapter

    }


}