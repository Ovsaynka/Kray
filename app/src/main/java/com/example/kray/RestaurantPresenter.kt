package com.example.kray

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kray.models.Restaurant
import com.example.kray.moduls.provideGetRestaurant
import com.example.kray.moduls.provideRetrofit
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


@InjectViewState
class RestaurantPresenter(private val getRestaurant: GetRestaurant) :
    MvpPresenter<RestaurantView>() {

    private val compositeDisposable = CompositeDisposable()

    fun fetchRestaurant() {

        val disposable = getRestaurant.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.setRestaurantList(it) },
                { it.printStackTrace() }
            )
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}