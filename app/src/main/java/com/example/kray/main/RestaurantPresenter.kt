package com.example.kray.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.kray.data.RestaurantApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RestaurantPresenter(private val restaurantApi: RestaurantApi) :
    MvpPresenter<MainPageView>() {

    private val compositeDisposable = CompositeDisposable()

    fun fetchRestaurant() {

        val disposable = restaurantApi.getRestaurants()
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