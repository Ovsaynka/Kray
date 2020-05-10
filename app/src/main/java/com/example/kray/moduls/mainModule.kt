package com.example.kray.moduls

import com.example.kray.main.RestaurantPresenter
import org.koin.dsl.module

val mainModule = module {
    factory { RestaurantPresenter(get()) }
}