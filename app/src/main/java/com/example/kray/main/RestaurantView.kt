package com.example.kray.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kray.data.Restaurant

@StateStrategyType(AddToEndSingleStrategy::class)
interface RestaurantView: MvpView {
    fun setRestaurantList(restaurants: List<Restaurant>)
}