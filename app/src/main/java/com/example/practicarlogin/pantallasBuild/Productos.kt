package com.example.practicarlogin.pantallasBuild

import MotherBoardpc
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.pantallasBuild.BuscarComponente.ListaAlmacenamiento
import com.example.practicarlogin.pantallasBuild.BuscarComponente.ListaProcesador
import com.example.practicarlogin.pantallasBuild.BuscarComponente.ListaRAM

private val language = LenguajeSeleccionado().idioma()

@Composable
fun productos(
    component: Int,
    function: (String) -> Unit,
    Volver: () -> Unit,
    viewModel: ComponentViewModel
) {


    val componentSeleccionado by viewModel.componentSeleccionado.observeAsState(initial = 0)
    Log.i("componente", "$componentSeleccionado")

    when (componentSeleccionado){
        0 -> ListaProcesador(function, viewModel, Volver, component)
        1-> MotherBoardpc(function, viewModel, Volver) { viewModel.lockProccesorOptions() }
        2-> ListaRAM(function,viewModel,Volver,component) { viewModel.lockBoardOptions() }
        3 -> ListaAlmacenamiento(function,viewModel,Volver,component) {}

    }


}


