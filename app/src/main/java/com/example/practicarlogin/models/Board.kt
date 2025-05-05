package com.example.practicarlogin.models

class Board(
    val nombre: String,
    val marca: String,
    val socket: String,
    val tipoMemoria: String,
    val chipset: String,
    val cantidadUSB: String,
    val slotsRam: Int,
    val factorForma: String,
    val puertosSata: Int,
    val puertosM2: Int,
    val permiteAPU: Boolean,
    val urlImagen: String,
    val precio : Double
){
    constructor() : this("", "", "", "", "", "", 0, "", 0, 0, false, "", 0.0)
}
