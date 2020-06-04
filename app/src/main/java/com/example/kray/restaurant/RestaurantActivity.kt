package com.example.kray.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.kray.R
import com.example.kray.data.Menu
import com.example.kray.data.Restaurant
import com.example.kray.restaurant.address.AddressFragment
import com.example.kray.restaurant.menu.MenuFragment
import com.example.kray.restaurant.menu.dishes.DishesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.btn_navigation.*

class RestaurantActivity : MvpAppCompatActivity(), RestaurantRouter {

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

    override fun navigateToDishes(menu: Menu) {
        val fragment = DishesFragment.newInstance(menu)
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
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
                    val fragment = AddressFragment.newInstance(restaurant)
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in_left,
                R.anim.fade_out
            )
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }


}