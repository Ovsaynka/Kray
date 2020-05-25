package com.example.kray.registration

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.kray.R
import com.example.kray.data.RestaurantApi
import com.example.kray.data.SignUpBody
import com.example.kray.data.UserBody
import com.example.kray.data.provideRetrofit
import kotlinx.android.synthetic.main.log_in_fragment.*
import kotlinx.android.synthetic.main.reg_in_fragment.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class RegistrationFragment: MvpAppCompatFragment(),
    RegistrationView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reg_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        signUpButton.setOnClickListener {
            val login = logInEditText.text.toString()
            val email = mailEditText.text.toString()
            val password1 = passwordEditText.text.toString()
            val password2 = passwordRepeatEditText2.text.toString()

            if(login == "" && email == "" && password1 == "" && password2 == "") {
                logInEditText.hint = "Please, enter login"
                mailEditText.hint = "Please, enter email"
                passwordEditText.hint = "Please, enter password"
                passwordRepeatEditText2.hint  = "Please, enter password"

                logInEditText.setHintTextColor(Color.RED)
                mailEditText.setHintTextColor(Color.RED)
                passwordEditText.setHintTextColor(Color.RED)
                passwordRepeatEditText2.setHintTextColor(Color.RED)

            } else if (login == "") {
                logInEditText.hint = "Please, enter login"
                logInEditText.setHintTextColor(Color.RED)
            } else if(email == "") {
                mailEditText.hint = "Please, enter email"
                mailEditText.setHintTextColor(Color.RED)
            } else if(password1 == "") {
                passwordEditText.hint = "Please, enter password"
                passwordEditText.setHintTextColor(Color.RED)
            } else if(password2 == "") {
                passwordRepeatEditText2.hint = "Please, enter password"
                passwordRepeatEditText2.setHintTextColor(Color.RED)
            } else {
                if(password1 != password2){
                    Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG)
                        .show()
                } else {
                    signup(login, email, password1)
                }
            }
         //   findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

    }

    private fun signup(login: String, email: String, password: String){
        val retIn = provideRetrofit().create(RestaurantApi::class.java)
        val registerInfo = SignUpBody(login, email, password)

        retIn.registerUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
                else{
                    Toast.makeText(context, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}