package com.example.practicarlogin.build_screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.build_screen.`detail-component`.MotherBoard
import com.example.practicarlogin.build_screen.`detail-component`.Psu
import com.example.practicarlogin.build_screen.`detail-component`.Storage
import com.example.practicarlogin.build_screen.`detail-component`.ViewRAM
import com.example.practicarlogin.build_screen.`detail-component`.procesador
import com.example.practicarlogin.build_screen.`detail-component`.viewCase
import com.example.practicarlogin.build_screen.`detail-component`.viewGrafica
import com.example.practicarlogin.models.Board
import com.example.practicarlogin.models.CPU
import com.example.practicarlogin.models.Caja
import com.example.practicarlogin.models.Graphic
import com.example.practicarlogin.models.RAM
import com.example.practicarlogin.models.fuente
import com.example.practicarlogin.models.storage
import com.example.practicarlogin.ui.theme.PracticarLoginTheme
import com.google.gson.Gson


val idiomaSeleccionado = LenguajeSeleccionado().idioma()


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun detalleProducto(
    producto: String,
    viewModel: ComponentViewModel,
    back: () -> Unit,
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
            0 -> procesador(component, viewModel, back,sharedTransitionScope,animatedVisibilityScope)
            1 -> MotherBoard(board, viewModel, back) { viewModel.lockProccesorOptions() }
            2 -> ViewRAM(ram, viewModel, back)
            3 -> Storage(storage, viewModel, back)
            4 -> viewGrafica(graphic, viewModel, back)
            5 -> viewCase(case, viewModel, back)
            6 -> Psu(psu,viewModel,back)
        }
    }

}




