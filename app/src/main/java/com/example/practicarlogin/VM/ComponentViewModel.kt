package com.example.practicarlogin.VM

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.RAM
import com.example.practicarlogin.piezas.storage

class ComponentViewModel : ViewModel() {
    private val _component = MutableLiveData<CPU?>()
    val component : LiveData<CPU?> get() = _component

    private val _Board = MutableLiveData<Board?>()
    val Board : LiveData<Board?> get() = _Board

    private val _ram = MutableLiveData<RAM?>()
    val ram : LiveData<RAM?> get() = _ram

    private val _storage = MutableLiveData<storage?>()
    val storage : LiveData<storage?> get() = _storage



    private val _componentSeleccionado = MutableLiveData<Int>(0)
    val componentSeleccionado : LiveData<Int> = _componentSeleccionado

    private val _procesador = MutableLiveData<Boolean>(false)
    val procesador : LiveData<Boolean> = _procesador

    private val _processorOptions = MutableLiveData<Boolean>(false)
    val processorOptions : LiveData<Boolean> = _processorOptions

    private val _BoardOptions = MutableLiveData<Boolean>(false)
    val BoardOptions : LiveData<Boolean> = _BoardOptions
    private val _placaBase = MutableLiveData<Boolean>(false)


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



    //borrar componente y guardar componente
    fun borrarRAM(){
        _ram.value = null
    }
    fun guardarRAM(ram : RAM){
        _ram.value  = ram
    }

    fun guardarBoard(board : Board){
        _Board.value = board
    }
    fun borrarBoard(){
        _Board.value = null
    }
    fun guardarCPU(cpu : CPU){
        _component.value = cpu
    }
    fun borrarCPU(){
        _component.value = null
    }
    //Borrar componente

    //Desbloquear candados lista
    fun unlockBoard(){
        _procesador.value = true
    }
    fun lockBoard(){
        _procesador.value = false
    }
    fun unlockRAM(){
        _RAM.value = true
    }
    fun lockRAM(){
        _RAM.value = false
    }
    fun unlockStorage(){
        _almacenamiento.value = true
    }
    fun lockStorage(){
        _almacenamiento.value = false
    }

    //bloquear acceso a eliminar y remplazar
    fun lockProccesorOptions(){
        _processorOptions.value = true
    }
    fun unlockProccesorOptions(){
        _processorOptions.value = false
    }
    fun lockBoardOptions(){
        _BoardOptions.value = true
    }
    fun unlockBoardOptions(){
        _BoardOptions.value = false
    }


    //Componente que seleccionamos
    fun cambiarComponente(component : Int){
        _componentSeleccionado.value = component
    }

}