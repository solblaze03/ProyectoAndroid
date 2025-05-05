package com.example.practicarlogin.build_screen

import MotherBoardpc
import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.language.LenguajeSeleccionado
import com.example.practicarlogin.build_screen.`search-component`.ListaAlmacenamiento
import com.example.practicarlogin.build_screen.`search-component`.ListaProcesador
import com.example.practicarlogin.build_screen.`search-component`.ListaPsu
import com.example.practicarlogin.build_screen.`search-component`.ListaRAM
import com.example.practicarlogin.build_screen.`search-component`.buscarChasis
import com.example.practicarlogin.build_screen.`search-component`.buscarGrafica
import com.example.practicarlogin.ui.theme.PracticarLoginTheme

private val language = LenguajeSeleccionado().idioma()

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun productos(
    component: Int,
    ComponenteSerializado: (String) -> Unit,
    Volver: () -> Unit,
    viewModel: ComponentViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    PracticarLoginTheme {

        val componentSeleccionado by viewModel.componentSeleccionado.observeAsState(initial = 0)
        Log.i("componente", "$componentSeleccionado")

        when (componentSeleccionado) {
            0 -> ListaProcesador(
                ComponenteSerializado,
                viewModel,
                Volver,
                component,
                sharedTransitionScope,
                animatedVisibilityScope
            )

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

            3 -> ListaAlmacenamiento(
                ComponenteSerializado,
                viewModel,
                Volver,
                component
            )
            4 -> buscarGrafica(ComponenteSerializado,viewModel,Volver,component)
            5 -> buscarChasis(ComponenteSerializado,viewModel,Volver,component)
            6 -> ListaPsu(ComponenteSerializado,viewModel,Volver,component)

        }
    }

}


