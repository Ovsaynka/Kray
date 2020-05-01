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

    private val mAdapter: RestaurantListAdapter = RestaurantListAdapter()
    private val compositeDisposable = CompositeDisposable()
    private var dataList: MutableList<Restaurant> = mutableListOf()
    private val response: Response<*>? = null

    fun fetchRestaurant() {
       /* val call: Call<Restaurant> = provideGetRestaurant(provideRetrofit()).getRestaurants()
        call.enqueue(object : Callback<Restaurant > {

            override fun onResponse(call: Call<List<Restaurant>>?, response: Response<List<Restaurant>>?) {
                val body = response?.body()?.toString()
                println(body)

                val json = GsonBuilder().create()

                val homedateList: List<Restaurant> = json.fromJson(body, Array<Restaurant>::class.java).toList()
//                getRestaurant.getRestaurants()
//                dataList.addAll(response!!.body()!!)
            }

            override fun onFailure(call: Call<Restaurant>, e: IOException) {

            }

        })*/

        val body = response?.body()?.toString()
        val gson = GsonBuilder().create()

        val homedata: List<Restaurant> = gson.fromJson(body, Array<Restaurant>::class.java).toList()
        //val homedata= gson.fromJson(body, Restaurant::class.java)

        val disposable = getRestaurant.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mAdapter.addItems(homedata)},//viewState.setRestaurantList(homedata) },
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