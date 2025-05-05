package com.example.practicarlogin.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.Login
import com.example.practicarlogin.`view-model`.LoginViewModel
import com.example.practicarlogin.*
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


val loginViewModel: LoginViewModel = LoginViewModel()

@Composable
fun NavigationWrapper() {
    val auth: FirebaseAuth = Firebase.auth
    val navController = rememberNavController()
    val opcion = 0
    val startDestination = if (auth.currentUser == null) {
        Login
    } else {
        pantallaInicial
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable<Login> {
            Login(
                loginViewModel,
                { navController.navigate(pantallaInicial) { popUpTo(0) } },
                auth,
                { navController.navigate(Registrar) })
        }
        composable<pantallaInicial> {
            pantallaInicial(loginViewModel)
        }
        composable<Registrar> {
            PantallaRegistrar(
                loginViewModel,
                auth,
                { navController.navigate(Login) { popUpTo(0) } },
                {
                    navController.navigate(
                        pantallaInicial
                    ) { popUpTo(0) }
                })
        }


    }


}