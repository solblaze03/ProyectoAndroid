package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.practicarlogin.PantallasNavegaciones.buildPC
import com.example.practicarlogin.productos

@Composable
fun navWrapperBuild(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenBuild.build){
        composable<ScreenBuild.build>{
            buildPC {component -> navController.navigate(ScreenBuild.procesador(component))}
        }
        composable<ScreenBuild.procesador> {e ->
            val valor  = e.toRoute<ScreenBuild.procesador>()
            productos(valor.component)
        }
    }
}