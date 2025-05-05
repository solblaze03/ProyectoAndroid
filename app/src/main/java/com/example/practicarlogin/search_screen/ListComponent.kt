package com.example.practicarlogin.search_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.`view-model`.SearchViewModel
import androidx.compose.runtime.getValue
import com.example.practicarlogin.search_screen.Productos.listaCpu
import com.example.practicarlogin.navigation.ComponenteElegido

@Composable
fun ListComponent(viewModell: SearchViewModel) {

    val component by viewModell.componenteSearch.observeAsState()
    val cpu by viewModell.cpu.observeAsState()



    when(component?.componente){
        ComponenteElegido.Procesador -> listaCpu(cpu,viewModell)
        else -> ""
    }


}
