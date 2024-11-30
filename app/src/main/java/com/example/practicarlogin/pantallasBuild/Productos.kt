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
import com.example.practicarlogin.ui.theme.PracticarLoginTheme

private val language = LenguajeSeleccionado().idioma()

@Composable
fun productos(
    component: Int,
    ComponenteSerializado: (String) -> Unit,
    Volver: () -> Unit,
    viewModel: ComponentViewModel
) {
    PracticarLoginTheme {

        val componentSeleccionado by viewModel.componentSeleccionado.observeAsState(initial = 0)
        Log.i("componente", "$componentSeleccionado")

        when (componentSeleccionado) {
            0 -> ListaProcesador(ComponenteSerializado, viewModel, Volver, component)
            1 -> MotherBoardpc(
                ComponenteSerializado,
                viewModel,
                Volver
            ) { viewModel.lockProccesorOptions() }

            2 -> ListaRAM(
                ComponenteSerializado,
                viewModel,
                Volver,
                component
            ) { viewModel.lockBoardOptions() }

            3 -> ListaAlmacenamiento(ComponenteSerializado, viewModel, Volver, component) {}

        }
    }

}


