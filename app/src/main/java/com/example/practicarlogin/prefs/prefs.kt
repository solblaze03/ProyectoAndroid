package com.example.practicarlogin.prefs

import android.app.Application
import android.content.SharedPreferences

class prefs : Application() {

    companion object{
        lateinit var prefs : Prefsidiomas
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefsidiomas(applicationContext)
    }
}