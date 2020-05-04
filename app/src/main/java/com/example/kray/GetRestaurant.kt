package com.example.kray

import android.database.Observable
import com.example.kray.models.Restaurant
import io.reactivex.ObservableConverter
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import java.util.*

interface GetRestaurant {
    @GET("/restaurant")
    fun getRestaurants(): Single<List<Restaurant>>
}