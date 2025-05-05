package com.example.practicarlogin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicarlogin.PantallasNavegaciones.Search
import com.example.practicarlogin.PantallasSearch.ListComponent
import com.example.practicarlogin.VM.SearchViewModel


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