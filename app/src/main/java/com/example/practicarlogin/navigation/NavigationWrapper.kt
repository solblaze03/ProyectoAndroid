package com.example.practicarlogin.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        NavHost(navController = navController, startDestination = Login, modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            composable<Login> {
                Login(loginViewModel) { navController.navigate(pantallaInicial) }
            }
            composable<pantallaInicial> {
                pantallaInicial(loginViewModel)
            }


        }
    }else {

        NavHost(navController = navController, startDestination = pantallaInicial, modifier = Modifier.background(MaterialTheme.colorScheme.background)) {

            composable<pantallaInicial> {
                pantallaInicial(loginViewModel)
            }


        }
    }

}