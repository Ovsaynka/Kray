package com.example.kray.moduls

import com.example.kray.Constants.BASE_URL
import com.example.kray.GetRestaurant
import com.example.kray.RestaurantPresenter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.cacheGet
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.koin.experimental.builder.getArguments
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single { provideRetrofit() }
    single { provideGetRestaurant(get()) }
}


fun provideRetrofit(): Retrofit {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    val client =
        OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

fun provideGetRestaurant(retrofit: Retrofit): GetRestaurant =
    retrofit.create(GetRestaurant::class.java)
