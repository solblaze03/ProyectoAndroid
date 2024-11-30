package com.example.practicarlogin.piezas

data class CPU(
    val nombre: String, val marca: String, val socket: String,
    val nucleos: Int,
    val hilos: Int,
    val precio: Double,
    val frecuencia: Double,
    val grafica: String,
    val tdp: Double,
    val tecnologia: String,
    val imagen: String)