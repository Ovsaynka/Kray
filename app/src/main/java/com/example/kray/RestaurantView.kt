package com.example.kray

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kray.models.Restaurant

@StateStrategyType(AddToEndSingleStrategy::class)
interface RestaurantView: MvpView {
    fun setRestaurantList(restaurants: MutableList<Restaurant>)
}