package com.example.kray.restaurant.menu

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kray.data.Restaurant

@StateStrategyType(AddToEndSingleStrategy::class)
interface MenuView: MvpView {
    fun loadData(restaurant: Restaurant)
}