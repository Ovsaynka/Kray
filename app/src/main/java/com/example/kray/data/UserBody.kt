package com.example.kray.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserBody(
    @SerializedName("username")val login: String?,
    @SerializedName("token")val token: String?
): Serializable