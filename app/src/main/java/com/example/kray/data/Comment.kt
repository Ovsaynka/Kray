package com.example.kray.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comment(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("comment")
    val comment: String?,

    @SerializedName("stars")
    val stars: Int?,

    @SerializedName("name")
    val name: String?

): Serializable