package com.example.kray.data

import com.google.gson.annotations.SerializedName

data class Dishes(
    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: Int?,

    @SerializedName("url")
    val image: String?
)