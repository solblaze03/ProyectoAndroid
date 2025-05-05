package com.example.practicarlogin.navigation

import kotlinx.serialization.Serializable

class ScreenBuild {

    @Serializable
    object build
    @Serializable
    data class procesador(val component: Int)
    @Serializable
    data class detalleComponente(val componente : String)
}