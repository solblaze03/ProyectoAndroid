package com.example.practicarlogin.models

class Caja(
    val nombre: String,
    val factorForma: String,
    val marca: String,
    val ventiladores: Int,
    val imagen: String,
    val peso: String,
    val rgb: Boolean,
    val longitudMaximaGPU: Double,
    val alturaGPU : Double,
    val longitudChasis : Double,
    val alturaChasis : Double,
    val precio: Double
)
{
    constructor() : this("", "", "", 0, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0)
}