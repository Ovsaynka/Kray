package com.example.kray.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurant(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("address") val address: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("phone") val phone:String?,
    @SerializedName("cuisines") val cuisines: Array<String?>?,
    @SerializedName("url") val image: String?,
    @SerializedName("longitude") val longtitude: Double?,
    @SerializedName("latitude") val latitude: Double?
): Serializable