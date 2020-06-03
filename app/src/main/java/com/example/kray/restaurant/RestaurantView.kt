package com.example.kray.restaurant

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.kray.data.Comment
import com.example.kray.data.Restaurant

@StateStrategyType(AddToEndSingleStrategy::class)
interface RestaurantView: MvpView {
    fun loadData(restaurant: Restaurant)
  //  fun setCommentList(comment: List<Comment>)
}