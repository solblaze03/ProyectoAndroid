package com.example.practicarlogin.VM

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarlogin.piezas.CPU

class ComponentViewModel : ViewModel() {
    private val _component = MutableLiveData<CPU?>()
    val component : LiveData<CPU?> get() = _component


    private val _procesador = MutableLiveData<Boolean>(false)
    val procesador : LiveData<Boolean> = _procesador

    private val _placaBase = MutableLiveData<Boolean>(true)
    val placaBase : LiveData<Boolean> = _placaBase

    private val _RAM = MutableLiveData<Boolean>(false)
    val RAM : LiveData<Boolean> = _RAM


    private val _almacenamiento = MutableLiveData<Boolean>(false)
    val almacenamiento : LiveData<Boolean> = _almacenamiento


    private val _tarjeta = MutableLiveData<Boolean>(false)
    val tarjeta : LiveData<Boolean> = _tarjeta


    private val _caja = MutableLiveData<Boolean>(false)
    val caja : LiveData<Boolean> = _caja


    private val _FuenteAlimentacion = MutableLiveData<Boolean>(false)
    val FuenteAlimentacion : LiveData<Boolean> = _FuenteAlimentacion


    fun guardarCPU(cpu : CPU){
        _component.value = cpu
    }
    fun borrarCPU(){
        _component.value = null
    }
    fun unlockBoard(){
        _procesador.value = true
    }
    fun lockBoard(){
        _procesador.value = false
    }

}