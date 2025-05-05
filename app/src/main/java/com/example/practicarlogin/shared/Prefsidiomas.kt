package com.example.practicarlogin.shared

import android.content.Context

class Prefsidiomas(context : Context) {
    val bd = "Prefs"
    val language = "idioma"

    val datos = context.getSharedPreferences(bd,0)

    fun guardarIdioma(idioma : Int){
        datos.edit().putInt(language,idioma).apply()
    }
    fun obtenerIdioma() : Int{
        return datos.getInt(language,-1) ?: -1
    }
}