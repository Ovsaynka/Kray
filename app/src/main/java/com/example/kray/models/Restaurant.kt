package com.example.kray.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurant(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("address") val address: String,
    @SerializedName("cuisines") val cuisines: Array<String>
): Serializable