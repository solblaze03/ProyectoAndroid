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
import com.example.practicarlogin.piezas.fuente
import com.example.practicarlogin.piezas.storage
import com.google.firebase.firestore.FirebaseFirestore

class ComponentViewModel : ViewModel() {


    //Lista procesadores
    private val db = FirebaseFirestore.getInstance()

    private val _cpus = MutableLiveData<List<CPU>>()
    val cpus: LiveData<List<CPU>> get() = _cpus
    private val _ListBoards = MutableLiveData<List<Board>>()
    val listBoards :  LiveData<List<Board>> = _ListBoards
    private val _ListRAM = MutableLiveData<List<RAM>>()
    val ListRam : LiveData<List<RAM>> = _ListRAM

    init {
        buscarCPU()
        buscarBoard()
        buscarRam()
    }
    fun buscarBoard(){
        val docRef = db.collection("Boards")
            .get()
            .addOnSuccessListener { documents ->
                val boardsList = mutableListOf<Board>()
                documents.forEach { e -> 
                    val Board = Board(
                        nombre = e.getString("nombre") ?: "",
                        marca = e.getString("marca") ?: "",
                        socket = e.getString("socket") ?: "",
                        tipoMemoria = e.getString("tipoMemoria") ?: "",
                        chipset = e.getString("chipset") ?: "",
                        cantidadUSB = e.getString("cantidadUSB") ?: "",
                        slotsRam = e.getLong("slotsRam")?.toInt() ?: 0,
                        factorForma = e.getString("factorForma") ?: "",
                        puertosSata = e.getLong("puertosSata")?.toInt() ?: 0,
                        puertosM2 = e.getLong("puertosM2")?.toInt() ?: 0 ,
                        permiteAPU = e.getBoolean("permiteAPU") ?: false,
                        urlImagen = e.getString("urlImagen") ?: "",
                        precio = e.getDouble("precio") ?: 0.0
                    )
                    boardsList.add(Board)
                }
                _ListBoards.value = boardsList
            }
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

    fun buscarRam(){
        val docRef = db.collection("Ram")
            .get()
            .addOnSuccessListener { document ->
                val listram = mutableListOf<RAM>()
                document.forEach {
                    val ram = RAM(
                        nombre = it.getString("nombre")!!,
                        marca = it.getString("marca")!!,
                        tipoMemoria = it.getString("tipoMemoria")!!,
                        cantidad = it.getLong("cantidad")!!.toInt() ,
                        velocidad = it.getLong("velocidad")!!.toInt(),
                        imagen = it.getString("imagen")!!,
                        precio = it.getDouble("precio")!!
                    )
                    listram.add(ram)
                }
                _ListRAM.value = listram
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

    private val _psuComponent = MutableLiveData<fuente?>()
    val psuComponent : LiveData<fuente?> = _psuComponent

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

    private val _graphicsOptions = MutableLiveData<Boolean>(false)
    val graphicOptions : LiveData<Boolean> = _graphicsOptions

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

    fun guardarPsu(fuente: fuente){
        _psuComponent.value = fuente

    }

    fun borrarPsu(){
     _psuComponent.value = null
    }

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