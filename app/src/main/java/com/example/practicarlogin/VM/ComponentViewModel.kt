package com.example.practicarlogin.VM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarlogin.piezas.Board
import com.example.practicarlogin.piezas.CPU
import com.example.practicarlogin.piezas.Caja
import com.example.practicarlogin.piezas.Graphic
import com.example.practicarlogin.piezas.RAM
import com.example.practicarlogin.piezas.storage
import com.google.firebase.firestore.FirebaseFirestore

class ComponentViewModel : ViewModel() {


    //Lista procesadores
    private val db = FirebaseFirestore.getInstance()
    private val _cpus = MutableLiveData<List<CPU>>()
    val cpus: LiveData<List<CPU>> get() = _cpus

    init {
        buscarCPU()
    }

    fun buscarCPU() {
        val docRef = db.collection("CPU").get()
            .addOnSuccessListener { documents ->
            val cpuList = mutableListOf<CPU>()
            for (document in documents) {
                val cpu = CPU(
                    nombre = document.getString("nombre") ?: "null",
                    marca = document.getString("marca") ?: "null",
                    socket = document.getString("socket") ?: "null",
                    nucleos = document.getLong("nucleos")!!.toInt() ,
                    hilos = document.getLong("hilos")!!.toInt(),
                    precio = document.getDouble("precio") ?: 0.0,
                    frecuencia = document.getDouble("frecuencia") ?: 0.0,
                    grafica = document.getString("grafica") ?: "null",
                    tdp = document.getDouble("tdp") ?: 0.0,
                    tecnologia =document.getString("tecnologia") ?: "null",
                    imagen = document.getString("imagen") ?: "null"
                )
                cpuList.add(cpu)
            }
            _cpus.value = cpuList
        }
    }


    //Componentes
    private val _component = MutableLiveData<CPU?>()
    val component: LiveData<CPU?> get() = _component

    private val _Board = MutableLiveData<Board?>()
    val Board: LiveData<Board?> get() = _Board

    private val _ram = MutableLiveData<RAM?>()
    val ram: LiveData<RAM?> get() = _ram

    private val _storage = MutableLiveData<storage?>()
    val storage: LiveData<storage?> get() = _storage

    private val _graphic = MutableLiveData<Graphic?>()
    val graphic : LiveData<Graphic?> = _graphic

    private val _chasis = MutableLiveData<Caja?>()
    val chasis : LiveData<Caja?> = _chasis


    private val _componentSeleccionado = MutableLiveData<Int>(0)
    val componentSeleccionado: LiveData<Int> = _componentSeleccionado



    private val _procesador = MutableLiveData<Boolean>(false)
    val procesador: LiveData<Boolean> = _procesador

    //deshabilitar opciones debajo
    private val _processorOptions = MutableLiveData<Boolean>(false)
    val processorOptions: LiveData<Boolean> = _processorOptions

    private val _BoardOptions = MutableLiveData<Boolean>(false)
    val BoardOptions: LiveData<Boolean> = _BoardOptions
    private val _placaBase = MutableLiveData<Boolean>(false)


    //habilitar el si
    val placaBase: LiveData<Boolean> = _placaBase

    private val _RAM = MutableLiveData<Boolean>(false)
    val RAM: LiveData<Boolean> = _RAM


    private val _almacenamiento = MutableLiveData<Boolean>(false)
    val almacenamiento: LiveData<Boolean> = _almacenamiento


    private val _tarjeta = MutableLiveData<Boolean>(false)
    val tarjeta: LiveData<Boolean> = _tarjeta


    private val _caja = MutableLiveData<Boolean>(false)
    val caja: LiveData<Boolean> = _caja

    private val _psu = MutableLiveData<Boolean>(false)
    val psu : LiveData<Boolean> = _psu

    private val _FuenteAlimentacion = MutableLiveData<Boolean>(false)
    val FuenteAlimentacion: LiveData<Boolean> = _FuenteAlimentacion




    //borrar componente y guardar componente

    fun guardarCaja(chasis: Caja){
        _chasis.value = chasis
    }
    fun borrarCaja(){
        _chasis.value = null
    }

    fun guardarGrafica(grafica: Graphic){
        _graphic.value = grafica
    }
    fun borrarGrafica(){
        _graphic.value = null
    }


    fun guardarAlmacenamiento(storage: storage) {
        _storage.value = storage
    }

    fun borrarAlmacenamiento() {
        _storage.value = null
    }


    fun borrarRAM() {
        _ram.value = null
    }

    fun guardarRAM(ram: RAM) {
        _ram.value = ram
    }

    fun guardarBoard(board: Board) {
        _Board.value = board
    }

    fun borrarBoard() {
        _Board.value = null
    }

    fun guardarCPU(cpu: CPU) {
        _component.value = cpu
    }

    fun borrarCPU() {
        _component.value = null
    }
    //Borrar componente

    //Desbloquear candados lista
    fun unlockBoard() {
        _procesador.value = true
    }

    fun lockBoard() {
        _procesador.value = false
    }

    fun unlockRAM() {
        _RAM.value = true
    }

    fun lockRAM() {
        _RAM.value = false
    }

    fun unlockStorage() {
        _almacenamiento.value = true
    }

    fun lockStorage() {
        _almacenamiento.value = false
    }

    fun unlockGraphic(){
        _tarjeta.value = true
    }
    fun lockGraphic(){
        _tarjeta.value = false
    }
    fun unlockCase(){
        _caja.value = true
    }
    fun lockCase(){
        _caja.value = false
    }
    fun unlockPsu(){
        _psu.value = true
    }
    fun lockPsu(){
        _psu.value = false
    }

    //bloquear acceso a eliminar y remplazar
    fun lockProccesorOptions() {
        _processorOptions.value = true
    }

    fun unlockProccesorOptions() {
        _processorOptions.value = false
    }

    fun lockBoardOptions() {
        _BoardOptions.value = true
    }

    fun unlockBoardOptions() {
        _BoardOptions.value = false
    }


    //Componente que seleccionamos
    fun cambiarComponente(component: Int) {
        _componentSeleccionado.value = component
    }

}