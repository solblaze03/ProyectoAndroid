package com.example.practicarlogin.piezas

data class Caja(
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