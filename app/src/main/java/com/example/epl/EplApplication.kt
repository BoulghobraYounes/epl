package com.example.epl

import android.app.Application

class EplApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPrefUtil.init(this)
    }

}