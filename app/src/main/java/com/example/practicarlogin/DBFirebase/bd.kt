package com.example.practicarlogin.DBFirebase

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.ListaPiezas
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var db: FirebaseFirestore

fun bd() {
    db = FirebaseFirestore.getInstance()
    //db.collection("Procesador").document()
    addCPU()
}

fun addCPU() {


    //Añadir lista

    ListaPiezas.listaFuentes
        .forEach { e ->


        db.collection("Fuente").add(e).addOnSuccessListener { e ->
            Log.i("firebase", "procesador agregado con id : ${e.id}")
        }.addOnFailureListener({ e ->
            Log.i("firebase", "Error al agregar el procesador: ${e}")
        })
    }



    //añadir uno
    /*
    val board = Board(
        nombre = "ASUS PRIME B760M-A WIFI",
        marca = "ASUS",
        socket = "LGA1700",
        tipoMemoria = "DDR5",
        chipset = "B760",
        cantidadUSB = "12",
        slotsRam = 4,
        factorForma = "mATX",
        puertosSata = 4,
        puertosM2 = 2,
        permiteAPU = true,
        urlImagen = "https://dlcdnwebimgs.asus.com/gain/9640f5a5-3273-4702-bde7-26a6385e8ca6/w692",
        precio = 144.99
    )

    db.collection("Boards").add(board).addOnSuccessListener { e ->
        Log.i("firebase", "procesador agregado con id : ${e.id}")
    }.addOnFailureListener({ e ->
        Log.i("firebase", "Error al agregar el procesador: ${e}")
    })

     */
}
