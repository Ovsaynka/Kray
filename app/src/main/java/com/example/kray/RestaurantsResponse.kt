package com.example.kray

import com.example.kray.models.Restaurant
import retrofit2.Call
import java.io.Serializable

data class RestaurantsResponse (
    val results: Array<Restaurant>
)