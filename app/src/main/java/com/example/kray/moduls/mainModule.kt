package com.example.kray.moduls

import com.example.kray.RestaurantPresenter
import org.koin.dsl.module

val mainModule = module {
    factory { RestaurantPresenter(get()) }
}