package com.example.kray.data

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

const val PARAM_ID_MENU = "id"

interface RestaurantApi {
    @GET("/restaurant")
    fun getRestaurants(): Single<List<Restaurant>>

    @POST("/auth/login")
    @Headers("Content-Type:application/json")
    fun signIn(@Body info: SignInBody): retrofit2.Call<UserBody>

    @Headers("Content-Type:application/json")
    @POST("/users/register")
    fun registerUser(
        @Body info: SignUpBody
    ): retrofit2.Call<ResponseBody>

    @POST("/commentRestaurant")
    fun getCommentRestaurant()
}