package com.example.kray

import io.reactivex.Single
import retrofit2.http.GET

interface GetRestaurant {

    @GET("/api")
    fun getRestaurant(): Single<List<Restaurant>>
}