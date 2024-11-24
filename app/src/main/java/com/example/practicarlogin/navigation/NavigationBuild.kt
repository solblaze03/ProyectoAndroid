package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.practicarlogin.PantallasNavegaciones.buildPC
import com.example.practicarlogin.VM.ComponentViewModel
import com.example.practicarlogin.pantallasBuild.detalleProducto
import com.example.practicarlogin.pantallasBuild.productos

private val viewModel: ComponentViewModel = ComponentViewModel()


@Composable
fun navWrapperBuild() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenBuild.build) {
        composable<ScreenBuild.build> {
            buildPC(viewModel) { component ->
                navController.navigate(
                    ScreenBuild.procesador(
                        component
                    )
                )
            }
        }
        composable<ScreenBuild.procesador> { e ->
            val valor = e.toRoute<ScreenBuild.procesador>()
            productos(valor.component,
                { e -> navController.navigate(ScreenBuild.detalleComponente(e)) },
                {
                    navController.navigate(ScreenBuild.build) {
                        popUpTo<ScreenBuild.build> {
                            inclusive = true
                        }
                    }
                },
                viewModel,

                )
        }
        composable<ScreenBuild.detalleComponente> { e ->
            val componente = e.toRoute<ScreenBuild.detalleComponente>()
            detalleProducto(componente.componente, viewModel) {
                navController.navigate(ScreenBuild.build) {
                    popUpTo<ScreenBuild.build> { inclusive = false }
                }
            }
        }


    }
}