package com.example.epl

import android.content.Context
import android.content.SharedPreferences
import com.squareup.picasso.BuildConfig

object SharedPrefUtil {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(".${BuildConfig.LIBRARY_PACKAGE_NAME}SharedPreferences", Context.MODE_PRIVATE)
    }

    fun setSoccerTileFavorite(id: String, value: Boolean){
        setBoolean(id, value)
    }

    fun getSoccerTileFavorite(id: String): Boolean{
        return getBoolean(id)
    }

    private fun setBoolean(name: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(name, value).apply()
    }

    private fun getBoolean(name: String, defaultValue : Boolean = false): Boolean{
        return sharedPreferences.getBoolean(name, defaultValue)
    }
}