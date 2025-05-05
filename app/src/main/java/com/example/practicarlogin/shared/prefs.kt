package com.example.practicarlogin.shared

import android.app.Application

class prefs : Application() {

    companion object{
        lateinit var prefs : Prefsidiomas
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefsidiomas(applicationContext)
    }
}