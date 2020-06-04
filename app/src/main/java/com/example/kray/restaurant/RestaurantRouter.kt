package com.example.kray.restaurant

import com.example.kray.data.Menu

interface RestaurantRouter{
    fun navigateToDishes(menu: Menu)
}