package com.example.practicarlogin.PantallasSearch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.practicarlogin.VM.SearchViewModel
import androidx.compose.runtime.getValue
import com.example.practicarlogin.PantallasSearch.Productos.listaCpu
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
