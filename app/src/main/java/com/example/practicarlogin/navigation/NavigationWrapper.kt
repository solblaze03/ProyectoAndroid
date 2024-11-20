package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.Login
import com.example.practicarlogin.VM.LoginViewModel
import com.example.practicarlogin.*
import com.example.practicarlogin.PantallasNavegaciones.buildPC


@Composable
fun NavigationWrapper(){
    val loginViewModel : LoginViewModel = LoginViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            Login(loginViewModel) {navController.navigate(pantallaInicial)}
        }
        composable<pantallaInicial>{
            pantallaInicial(loginViewModel)
        }
        composable<build>{
            buildPC() {navController.navigate(productos)}
        }
        composable<productos>{
            productos()
        }
    }
}