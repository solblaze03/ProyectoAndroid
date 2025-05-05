package com.example.practicarlogin.models

class Graphic(
    val nombre: String,
    val marca: String,
    val consumo: Double,
    val vram: String,
    val imagen: String,
    val tipoMemoria: String,
    val rtx: Boolean,
    val conectoresPantalla: String,
    val precio: Double,
    val altura : Double,
    val longitud : Double,
    val ensamblador: String,
    val rendimientoMinimo: Double
)
{
    constructor() : this("", "", 0.0, "", "", "", false, "", 0.0, 0.0, 0.0, "", 0.0)

}