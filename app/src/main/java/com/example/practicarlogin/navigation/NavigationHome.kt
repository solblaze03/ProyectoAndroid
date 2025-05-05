package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.Home.PrincipalHome
import com.example.practicarlogin.`view-model`.ComponentViewModel

import com.example.practicarlogin.navigation.homeScreens.HomeScreens



@Composable
fun NavigationHome(componentViewModel: ComponentViewModel) {



    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreens.Home) {
        composable<HomeScreens.Home>{
            PrincipalHome(componentViewModel)
        }
    }

}

