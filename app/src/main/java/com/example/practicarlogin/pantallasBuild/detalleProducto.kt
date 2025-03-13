package com.example.practicarlogin.pantallasBuild

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.pantallasBuild.vistas.MotherBoard
import com.example.practicarlogin.pantallasBuild.vistas.Psu
import com.example.practicarlogin.pantallasBuild.vistas.Storage
import com.example.practicarlogin.pantallasBuild.vistas.ViewRAM
import com.example.practicarlogin.pantallasBuild.vistas.procesador
import com.example.practicarlogin.pantallasBuild.vistas.viewCase
import com.example.practicarlogin.pantallasBuild.vistas.viewGrafica
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.Caja
import com.example.practicarlogin.piezas.Graphic
import com.example.practicarlogin.piezas.RAM
import com.example.practicarlogin.piezas.fuente
import com.example.practicarlogin.piezas.storage
import com.example.practicarlogin.ui.theme.PracticarLoginTheme
import com.google.gson.Gson


val idiomaSeleccionado = LenguajeSeleccionado().idioma()


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun detalleProducto(
    producto: String,
    viewModel: ComponentViewModel,
    function: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    PracticarLoginTheme {
        val component = Gson().fromJson(producto, CPU::class.java)
        val board = Gson().fromJson(producto, Board::class.java)
        val ram = Gson().fromJson(producto, RAM::class.java)
        val storage = Gson().fromJson(producto, storage::class.java)
        val graphic = Gson().fromJson(producto, Graphic::class.java)
        val case = Gson().fromJson(producto,Caja::class.java)
        val psu = Gson().fromJson(producto,fuente::class.java)
        val componenteSeleccionado by viewModel.componentSeleccionado.observeAsState(0)


        when (componenteSeleccionado) {
            0 -> procesador(component, viewModel, function,sharedTransitionScope,animatedVisibilityScope)
            1 -> MotherBoard(board, viewModel, function) { viewModel.lockProccesorOptions() }
            2 -> ViewRAM(ram, viewModel, function)
            3 -> Storage(storage, viewModel, function)
            4 -> viewGrafica(graphic, viewModel, function)
            5 -> viewCase(case, viewModel, function)
            6 -> Psu(psu,viewModel,function)
        }
    }

}




