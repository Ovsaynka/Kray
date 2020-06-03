package com.example.kray.restaurant.menu.dishes

import com.arellomobile.mvp.MvpView
import com.example.kray.data.Dishes

interface DishesView: MvpView {
    fun loadData(dish: Dishes)
}