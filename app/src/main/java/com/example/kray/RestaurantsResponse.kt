package com.example.kray

import com.example.kray.models.Restaurant
import java.io.Serializable

data class RestaurantsResponse (
    val results: MutableList<Restaurant>
): Serializable