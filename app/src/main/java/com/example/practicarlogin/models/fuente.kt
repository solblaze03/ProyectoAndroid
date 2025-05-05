package com.example.practicarlogin.models

class fuente(
    val nombre: String,
    val marca: String,
    val potencia: String,
    val certificacion: String,
    val conectores: String,
    val precio: Double,
    val imagen : String,
    val modularidad: String
){
    constructor() : this("", "", "", "", "", 0.0, "", "")
}
