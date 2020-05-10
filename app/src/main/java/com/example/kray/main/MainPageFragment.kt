package com.example.kray.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.kray.R
import com.example.kray.data.Restaurant
import kotlinx.android.synthetic.main.main_page_fragment.*
import org.koin.android.ext.android.get

class MainPageFragment : MvpAppCompatFragment(),
    RestaurantView {

    @InjectPresenter
    lateinit var mPresenter: RestaurantPresenter

    @ProvidePresenter
    fun provideRestaurantPresenter() = get<RestaurantPresenter>()

    private val mAdapter: RestaurantListAdapter =
        RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restaurantRecyclerView.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        restaurantRecyclerView.adapter = mAdapter
        mPresenter.fetchRestaurant()

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                search(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun search(s: String?) {
        mAdapter.search(s) {
            Toast.makeText(view?.context, "Nothing Found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setRestaurantList(restaurants: List<Restaurant>) {
        mAdapter.addItems(restaurants)
    }

}