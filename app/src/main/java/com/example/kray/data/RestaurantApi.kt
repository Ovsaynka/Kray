package com.example.kray.data

import io.reactivex.Single
import retrofit2.http.GET

interface RestaurantApi {
    @GET("/restaurant")
    fun getRestaurants(): Single<List<Restaurant>>
}