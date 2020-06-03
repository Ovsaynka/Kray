package com.example.kray.data

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Menu(
    @SerializedName("id") val id: Int?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("dishes")
    val dishes: ArrayList<Dishes?>
): Serializable