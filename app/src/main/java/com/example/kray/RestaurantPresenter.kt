package com.example.kray

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RestaurantPresenter(private val getRestaurant: GetRestaurant) :
    MvpPresenter<RestaurantView>() {

    private val mAdapter: RestaurantListAdapter = RestaurantListAdapter()
    private val compositeDisposable = CompositeDisposable()

    fun fetchRestaurant() {
        val disposable = getRestaurant.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.setRestaurantList(it.results) },
               { it.printStackTrace() }
            )
        compositeDisposable.add(disposable)
    }
//mAdapter.addItems(it.results)},
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}