package com.example.practicarlogin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.practicarlogin.prefs.prefs
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.practicarlogin.R
import com.example.practicarlogin.language.StringsEnglish
import com.example.practicarlogin.language.StringsSpanish
import com.example.practicarlogin.language.StringsValenciano
import com.example.practicarlogin.language.languages
import com.example.practicarlogin.languageSelect
import org.intellij.lang.annotations.Language

data class myAppLevelDestination(val route: String, val iconTextId: String)

private lateinit var languages: languages
private val spanish = StringsSpanish
private val english = StringsEnglish
private val valenciano = StringsValenciano



    fun idioma() : languages{
        val seleccionIdioma: List<languages> = listOf(
            spanish,
            english,
            valenciano
        )
        if (prefs.prefs.obtenerIdioma() != -1) {

            languageSelect = seleccionIdioma[prefs.prefs.obtenerIdioma()]
        } else {
            languageSelect = seleccionIdioma[0]
        }
        return languageSelect
    }


val topLevel = listOf(
    myAppLevelDestination(
        route = myappRoute.home,
        iconTextId = idioma().home
    ),
    myAppLevelDestination(
        route = myappRoute.build,
        iconTextId = idioma().build
    ),
    myAppLevelDestination(
        route = myappRoute.account,
        iconTextId = idioma().perfil
    )

)

class NavigationActions (private val navController: NavController){
    fun navigatedTo(destination : myAppLevelDestination){
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }

            launchSingleTop = true
        }
    }
}

object myappRoute{
    const val build = "build"
    const val account = "Settings"
    const val home = "home"
}