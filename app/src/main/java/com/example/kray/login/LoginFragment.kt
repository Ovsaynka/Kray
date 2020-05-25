package com.example.kray.login

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.beust.klaxon.Klaxon
import com.example.kray.R
import com.example.kray.data.RestaurantApi
import com.example.kray.data.SignInBody
import com.example.kray.data.UserBody
import com.example.kray.data.provideRetrofit
import com.example.kray.login.LoginView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.log_in_fragment.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Thread.sleep


class LoginFragment : MvpAppCompatFragment(),
    LoginView {
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
            val login = mailEnter.text.toString()
            val password = loginEnter.text.toString()

            if(login == "" && password == ""){

                mailEnter.hint = "Please, enter login"
                mailEnter.setHintTextColor(Color.RED)
                loginEnter.hint = "Please, enter password"
                loginEnter.setHintTextColor(Color.RED)

            } else if(login == "") {

                loginEnter.hint = "Please, enter password"
                loginEnter.setHintTextColor(Color.RED)

            } else if(password == "") {

                mailEnter.hint = "Please, enter login"
                mailEnter.setHintTextColor(Color.RED)

            }  else {
                signin(login, password)
            }
            //findNavController().navigate(R.id.action_loginFragment_to_mainPageFragment)
        }
        createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        backButton2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_startPageFragment2)
        }
    }

    private fun signin(email: String, password: String) {
        val retIn = provideRetrofit().create(RestaurantApi::class.java)
        val signInInfo = SignInBody(email, password)
        retIn.signIn(signInInfo).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                  findNavController().navigate(R.id.action_loginFragment_to_mainPageFragment)
                } else {
                    Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })

      //  return UserBody
    }
}