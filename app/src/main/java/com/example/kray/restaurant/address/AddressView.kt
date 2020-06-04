package com.example.kray.restaurant.address

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kray.data.Restaurant

@StateStrategyType(AddToEndSingleStrategy::class)
interface AddressView: MvpView {
    fun loadData(restaurant: Restaurant)
}