package com.example.practicarlogin.navigation

import com.example.practicarlogin.piezas.CPU
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

class ScreenBuild {

    @Serializable
    object build
    @Serializable
    data class procesador(val component: Int)
    @Serializable
    data class detalleComponente(val componente : String)
}