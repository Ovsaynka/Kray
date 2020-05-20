package com.example.kray.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import kotlinx.android.synthetic.main.start_page_fragment.*

class StartPageFragment: MvpAppCompatFragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        get_startedButton.setOnClickListener {
          findNavController().navigate(R.id.action_startPageFragment_to_mainPageFragment)
        }
    }
}