package com.example.practicarlogin.DBFirebase

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.ListaPiezas
import com.google.firebase.firestore.FirebaseFirestore







private lateinit var db : FirebaseFirestore

fun bd(){
    db = FirebaseFirestore.getInstance()
    db.collection("Procesador").document()
    addCPU()
}

fun addCPU(){
    /*
    ListaPiezas.procesadores
        .forEach { e ->


        db.collection("Storage").add(e).addOnSuccessListener { e ->
            Log.i("firebase", "procesador agregado con id : ${e.id}")
        }.addOnFailureListener({ e ->
            Log.i("firebase", "Error al agregar el procesador: ${e}")
        })
    }

     */
}
