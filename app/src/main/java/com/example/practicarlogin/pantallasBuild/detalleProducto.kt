package com.example.practicarlogin.pantallasBuild

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.pantallasBuild.vistas.MotherBoard
import com.example.practicarlogin.pantallasBuild.vistas.procesador
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.RAM
import com.google.gson.Gson


val idiomaSeleccionado = LenguajeSeleccionado().idioma()


@Composable
fun detalleProducto(producto: String, viewModel: ComponentViewModel, function: () -> Unit) {
    val component = Gson().fromJson(producto, CPU::class.java)
    val board = Gson().fromJson(producto, Board::class.java)
    val ram = Gson().fromJson(producto, RAM::class.java)

    val componenteSeleccionado by viewModel.componentSeleccionado.observeAsState(0)


    when (componenteSeleccionado){
        0 -> procesador(component, viewModel, function)
        1 -> MotherBoard(board,viewModel,function) {viewModel.lockProccesorOptions()}
    }

}




