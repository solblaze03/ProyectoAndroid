package com.example.practicarlogin.language

import com.example.practicarlogin.languageSelect
import com.example.practicarlogin.shared.prefs

class LenguajeSeleccionado {

    private lateinit var languages: languages
    private val spanish = StringsSpanish
    private val english = StringsEnglish
    private val valenciano = StringsValenciano



    fun idioma() : languages{
        val seleccionIdioma: List<languages> = listOf(
            spanish,
            english,
            valenciano
        )
        if (prefs.prefs.obtenerIdioma() != -1) {

            languageSelect = seleccionIdioma[prefs.prefs.obtenerIdioma()]
        } else {
            languageSelect = seleccionIdioma[0]
        }
        return languageSelect
    }

}