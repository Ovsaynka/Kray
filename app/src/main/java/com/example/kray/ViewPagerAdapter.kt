package com.example.kray

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kray.restaurant.RestaurantFragment
import com.example.kray.restaurant.address.AddressFragment
import com.example.kray.restaurant.menu.MenuFragment

class ViewPagerAdapter(fm: FragmentManager?, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm!!, lifecycle) {
    private val count = 3

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RestaurantFragment()
            1 -> fragment = MenuFragment()
            2 -> fragment = AddressFragment()
        }
        return fragment!!
    }

    override fun getItemCount(): Int {
        return count
    }
}