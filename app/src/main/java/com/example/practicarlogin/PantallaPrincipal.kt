package com.example.practicarlogin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.practicarlogin.principal_screen_navigation.profile
import com.example.practicarlogin.`view-model`.LoginViewModel
import com.example.practicarlogin.navigation.NavigationActions
import com.example.practicarlogin.navigation.myAppLevelDestination
import com.example.practicarlogin.navigation.myappRoute
import com.example.practicarlogin.navigation.navWrapperBuild
import com.example.practicarlogin.navigation.topLevel
import androidx.lifecycle.ViewModel
import com.example.practicarlogin.principal_screen_navigation.Cart
import com.example.practicarlogin.`view-model`.ComponentViewModel
import com.example.practicarlogin.navigation.NavigationHome
import com.example.practicarlogin.navigation.NavigationSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private val componentViewModel = ComponentViewModel();

@Composable
fun pantallaInicial(loginViewModel: LoginViewModel) {

    val user by loginViewModel.user.observeAsState("")
    val navController = rememberNavController()
    val navigateAction = remember(navController) {
        NavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: myappRoute.build

    MyAppContent(
        navController = navController,
        selectedDestination = selectedDestination,
        navigateTopLevelDestination = navigateAction::navigatedTo
    )
}


class NavigationViewModel : ViewModel() {
    private val _isBottomNavigationVisible = MutableStateFlow(true)
    val isBottomNavigationVisible: StateFlow<Boolean> get() = _isBottomNavigationVisible

    fun setBottomNavigationVisible(visible: Boolean) {
        _isBottomNavigationVisible.value = visible
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun MyAppContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateTopLevelDestination: (myAppLevelDestination) -> Unit
) {


    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            NavHost(
                modifier = Modifier
                    .weight(1f)
                    .height(30.dp).background(color = MaterialTheme.colorScheme.background),
                navController = navController,
                startDestination = myappRoute.build
            ) {
                composable(myappRoute.home) {
                    NavigationHome(componentViewModel)

                }
                composable(myappRoute.search) {
                    NavigationSearch()
                }
                composable(myappRoute.build) {
                    navWrapperBuild()
                }

                composable(myappRoute.carrito) {
                    Cart()
                }
                composable(myappRoute.account) {
                    profile()
                }

            }



                cargarUI(
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateTopLevelDestination
                )

        }
    }
}

@Composable
fun cargarUI(
    selectedDestination: String,
    navigateTopLevelDestination: (myAppLevelDestination) -> Unit,
) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth())
    NavigationBar(modifier = Modifier.fillMaxWidth(), containerColor = MaterialTheme.colorScheme.background) {
        topLevel.forEach { destination ->

            NavigationBarItem(

                selected = selectedDestination == destination.route,
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.Transparent,
                    disabledIconColor = Color.Transparent,
                    selectedTextColor = Color.Transparent,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Transparent,
                    unselectedTextColor = Color.Transparent,
                    disabledTextColor = Color.Transparent
                ),
                onClick = { navigateTopLevelDestination(destination) },

                icon = {
                    Icon(
                        imageVector = if (selectedDestination == destination.route) {
                            imagenSeleccionada(destination.iconTextId)
                        } else {
                            imagen(destination.iconTextId)
                        },
                        contentDescription = destination.iconTextId,
                        tint = if (selectedDestination == destination.route) {
                            colorResource(R.color.blue)
                        } else {
                            MaterialTheme.colorScheme.inverseSurface
                        },
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = {
                    Text(
                        destination.iconTextId,
                        color = if (selectedDestination == destination.route) {
                            colorResource(R.color.blue)
                        } else {
                            MaterialTheme.colorScheme.inverseSurface
                        }
                    )
                }
            )

        }
    }

}

@Composable
fun imagen(imagen: String): ImageVector {
    var imagenSelec: ImageVector = Icons.Default.Add
    when (imagen) {
        "Home", "Inici", "Inicio" -> imagenSelec =
            ImageVector.vectorResource(R.drawable.homeout)

        "Build", "Acoblar", "Ensamblar" -> imagenSelec =
            ImageVector.vectorResource(R.drawable.buildout)

        "Profile", "Perfil" -> imagenSelec = Icons.Default.AccountCircle

        "Cart", "Carrito" -> imagenSelec = ImageVector.vectorResource(R.drawable.cartoutlined)
        "Search", "Buscar" -> imagenSelec = Icons.Outlined.Search
    }
    return imagenSelec
}

@Composable
fun imagenSeleccionada(imagen: String): ImageVector {
    var imagenSelec: ImageVector = Icons.Default.Add
    when (imagen) {
        "Home", "Inici", "Inicio" -> imagenSelec = ImageVector.vectorResource(R.drawable.homein)
        "Build", "Acoblar", "Ensamblar" -> imagenSelec =
            ImageVector.vectorResource(R.drawable.buildin)

        "Profile", "Perfil" -> imagenSelec = Icons.Default.AccountCircle

        "Cart", "Carrito" -> imagenSelec = ImageVector.vectorResource(R.drawable.cartfilled)
        "Search", "Buscar" -> imagenSelec = Icons.Filled.Search
    }
    return imagenSelec
}
