package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.Login
import com.example.practicarlogin.VM.LoginViewModel
import com.example.practicarlogin.*
import com.example.practicarlogin.PantallasNavegaciones.buildPC

val loginViewModel: LoginViewModel = LoginViewModel()

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()
    val opcion = 1
    if (opcion == 0) {
        NavHost(navController = navController, startDestination = Login) {
            composable<Login> {
                Login(loginViewModel) { navController.navigate(pantallaInicial) }
            }
            composable<pantallaInicial> {
                pantallaInicial(loginViewModel)
            }


        }
    }else {

        NavHost(navController = navController, startDestination = pantallaInicial) {

            composable<pantallaInicial> {
                pantallaInicial(loginViewModel)
            }


        }
    }

}