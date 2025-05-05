package com.example.practicarlogin.models

import com.example.practicarlogin.navigation.ComponenteElegido

data class Componente(val nombre: String, val image: Int,val componente: ComponenteElegido)
data class ComponentDescription(val nombre: String, val image: String, val precio: Double, val component : String)