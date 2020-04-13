package com.example.kray.models

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("cuisines") val cuisines: Cuisines
)