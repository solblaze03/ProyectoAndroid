package com.example.practicarlogin.models

class storage(
    val nombre: String,
    val marca: String,
    val tipo: String,
    val tipoDisco: String,
    val tamaño: String,
    val Vl: String,
    val VE: String,
    val imagen: String,
    val precio: Double
){
    constructor() : this("", "", "", "", "", "", "", "", 0.0)

}