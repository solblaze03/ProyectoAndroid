package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.principal_screen_navigation.Search
import com.example.practicarlogin.search_screen.ListComponent
import com.example.practicarlogin.`view-model`.SearchViewModel


private val searchViewModel: SearchViewModel = SearchViewModel()
@Composable
fun NavigationSearch(){



    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreensSearch.PantallaInicial) {
        composable<ScreensSearch.PantallaInicial> {
            Search({navController.navigate(ScreensSearch.componenteElegido)},searchViewModel )
        }
        composable<ScreensSearch.componenteElegido> {

            ListComponent(searchViewModel)


        }
    }
}