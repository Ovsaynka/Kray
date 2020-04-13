package com.example.kray

import com.example.kray.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    single { provideRetrofit() }
    single { provideGetRestaurant(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideGetRestaurant(retrofit: Retrofit): GetRestaurant =
    retrofit.create(GetRestaurant::class.java)
