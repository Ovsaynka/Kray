package com.example.kray

import com.arellomobile.mvp.MvpPresenter
import com.example.kray.models.Restaurant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RestaurantPresenter(private val getRestaurant: GetRestaurant) :
    MvpPresenter<RestaurantView>() {

    private val compositeDisposable = CompositeDisposable()

    fun fetchRestaurant() {
        val disposable = getRestaurant.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                //{ viewState.setRestaurantList(it.) },
               // { it.printStackTrace() }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}