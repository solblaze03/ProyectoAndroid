package com.example.practicarlogin.models

class RAM(
    val nombre: String,
    val marca : String,
    val tipoMemoria: String,
    val cantidad: Int,
    val velocidad: Int,
    val imagen: String,
    val precio: Double
)
{
    constructor() : this("", "", "", 0, 0, "", 0.0)

}