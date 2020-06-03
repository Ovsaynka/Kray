package com.example.kray.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Restaurant(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("address")
    val address: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("phone")
    val phone:String?,

    @SerializedName("stars")
    val stars: Int?,

    @SerializedName("longitude")
    val longtitude: Double?,

    @SerializedName("latitude")
    val latitude: Double?,

    @SerializedName("url")
    val image: String?,

    @SerializedName("avgCheck")
    val avgCheck: Int?,

    @SerializedName("menu")
    val menu: ArrayList<Menu?>,

    @SerializedName("commentRestaurants")
    val comments: Array<Comment?>

) : Serializable
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Restaurant

        if (id != other.id) return false
        if (name != other.name) return false
        if (address != other.address) return false
        if (description != other.description) return false
        if (image != other.image) return false
        if (avgCheck != other.avgCheck) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (avgCheck ?: 0)
        return result
    }
}
