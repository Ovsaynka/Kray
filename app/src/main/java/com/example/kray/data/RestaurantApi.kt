package com.example.kray.data

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

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

    @Headers("Content-Type:application/json")
    @POST("/commentRestaurant/addCommentRestaurant")
    fun sendComment(
        @Body comment: CommentToSend,
        @Query("idRestaurant") idRestaurant: Int,
        @Query("idUser") idUser: Int,
        @Header("Authorization") token: String
    ): retrofit2.Call<ResponseBody>

}