package com.example.kray

import com.example.kray.models.Restaurant
import io.reactivex.Single
import retrofit2.http.GET

interface GetRestaurant {

    @GET("/restaurant")
    fun getRestaurants(): Single<RestaurantsResponse>
}