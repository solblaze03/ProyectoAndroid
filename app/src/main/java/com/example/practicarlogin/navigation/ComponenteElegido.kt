package com.example.practicarlogin.navigation

sealed class ComponenteElegido {
    object Procesador: ComponenteElegido()
    object PlacaBase: ComponenteElegido()
    object RAMOrdenador : ComponenteElegido()
    object Almacenamiento : ComponenteElegido()
    object TarjetaGrafica : ComponenteElegido()
    object CajaOrdenador : ComponenteElegido()
    object Fuente: ComponenteElegido()
    object Mouse: ComponenteElegido()
    object Keyboard: ComponenteElegido()
    object Monitor: ComponenteElegido()
    object HeadPhones: ComponenteElegido()
    object MousePad: ComponenteElegido()
    object Chair : ComponenteElegido()
}