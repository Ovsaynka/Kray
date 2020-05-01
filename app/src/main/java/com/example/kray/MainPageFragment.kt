package com.example.kray

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kray.models.Restaurant
import kotlinx.android.synthetic.main.main_page_fragment.*
import org.koin.android.ext.android.get

class MainPageFragment : MvpAppCompatFragment(),  RestaurantView{

    @InjectPresenter
    lateinit var mPresenter: RestaurantPresenter

    @ProvidePresenter
    fun provideRestaurantPresenter() = get<RestaurantPresenter>()

    private var mAdapter: RestaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val searchBar =
            view.findViewById(R.id.searchView) as SearchView

        mPresenter.fetchRestaurant()
        restaurantRecyclerView.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL, false)
        restaurantRecyclerView.adapter = mAdapter
    }

    override fun setRestaurantList(restaurants: List<Restaurant>) {
        mAdapter.addItems(restaurants)
    }

}