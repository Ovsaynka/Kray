package com.example.kray.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.kray.R
import com.example.kray.data.Restaurant
import com.example.kray.restaurant.address.AddressFragment
import com.example.kray.restaurant.menu.MenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.btn_navigation.*

class RestaurantActivity : MvpAppCompatActivity() {

    lateinit var restaurant: Restaurant

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.btn_navigation)
        NavHostFragment.create(R.navigation.nav_graph)

        restaurant = RestaurantActivityArgs.fromBundle(intent.extras!!).restaurant

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = RestaurantFragment.newInstance(restaurant)
        addFragment(fragment)
        backButton.setOnClickListener {
            finish()
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_info -> {
                    val fragment = RestaurantFragment.newInstance(restaurant)
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_menu -> {
                    val fragment = MenuFragment.newInstance(restaurant)
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_address -> {
                    val fragment = AddressFragment.newInstance()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }


}