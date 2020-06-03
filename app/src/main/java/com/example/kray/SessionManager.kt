package com.example.kray

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SessionManager {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var con: Context
    var PRIVATE_MODE: Int = 0

    constructor(con: Context){
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object{

        val PREF_NAME: String = "KotlinDemo"
        val IS_LOGIN: String = "isLoggedIn"
        val KEY_USERNAME: String = "userName"
        val KEY_TOKEN: String = "token"
        val KEY_USER_ID: String = "id"

    }

    fun createLoginSession(userName: String, token: String, id: String){
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USERNAME, userName)
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_USER_ID, id)
        editor.commit()
    }

    fun checkLogin() {
        if(!this.isLoggedIn()) {
            var i: Intent = Intent(con, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          //  con.startActivity(i)
        }
    }

     fun getUserDetails(): Map<String, String> {

        val user: Map<String, String> = HashMap<String, String>()

        pref.getString(KEY_USERNAME, null)?.let { (user as HashMap).put(KEY_USERNAME, it) }
        pref.getString(KEY_TOKEN, null)?.let { (user as HashMap).put(KEY_TOKEN, it) }
         pref.getString(KEY_USER_ID, null)?.let { (user as HashMap).put(KEY_USER_ID, it) }

        return user
    }
    fun LogoutUser() {

        editor.clear()
        editor.commit()

        var i: Intent = Intent(con, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        con.startActivity(i)
    }

    fun isLoggedIn(): Boolean{

        return pref.getBoolean(IS_LOGIN,false)
    }
}