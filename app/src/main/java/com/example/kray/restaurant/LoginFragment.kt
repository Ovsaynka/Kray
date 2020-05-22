package com.example.kray.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import kotlinx.android.synthetic.main.log_in_fragment.*

class LoginFragment : MvpAppCompatFragment(), LoginView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.log_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signIn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainPageFragment)
        }
        createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        backButton2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_startPageFragment2)
        }
    }
}