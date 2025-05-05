package com.example.practicarlogin.`view-model`

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicarlogin.models.Board
import com.example.practicarlogin.models.Brands
import com.example.practicarlogin.models.CPU
import com.example.practicarlogin.models.Caja
import com.example.practicarlogin.models.Component
import com.example.practicarlogin.models.ComponentDescription
import com.example.practicarlogin.models.Graphic
import com.example.practicarlogin.models.RAM
import com.example.practicarlogin.models.fuente
import com.example.practicarlogin.models.storage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch

class ComponentViewModel : ViewModel() {


    //Lista procesadores
    private val db = FirebaseFirestore.getInstance()

    private val _cpus = MutableLiveData<List<CPU>>()
    val cpus: LiveData<List<CPU>> get() = _cpus

    private val _ListBoards = MutableLiveData<List<Board>>()
    val listBoards: LiveData<List<Board>> = _ListBoards
    private val _ListRAM = MutableLiveData<List<RAM>>()
    val ListRam: LiveData<List<RAM>> = _ListRAM

    private val _ssdList = MutableLiveData<List<storage>>()
    val ssdlist: LiveData<List<storage>> = _ssdList

    private val _graphicList = MutableLiveData<List<Graphic>>()
    val graphiclist: LiveData<List<Graphic>> = _graphicList

    private val _componentsDescription = MutableLiveData<MutableList<ComponentDescription>>()
    val componentsDescription: LiveData<MutableList<ComponentDescription>> = _componentsDescription

    private val _componentsWithLimit = MutableLiveData<MutableList<ComponentDescription>>()
    val componentsWithLimit: LiveData<MutableList<ComponentDescription>> = _componentsWithLimit

    private val _componentNameSelected = MutableLiveData<String>()
    val componentNameSelected: LiveData<String> = _componentNameSelected

    private val _componentSelected = MutableLiveData<String>()
    val componentSelected: LiveData<String> = _componentSelected

    init {

        //initTheBestOfBrand()
        initAllComponents()

    }



    fun initAllComponents() {
        _componentsWithLimit.value = mutableListOf()
        getAllComponentsByLimit(5)
    }

    fun initTheBestOfBrand() {
        val brand = Brands.entries.toTypedArray()

        _componentsDescription.value = mutableListOf()
        //_componentNameSelected.value = marca.random()
        val random = brand.indices.random()

        _componentNameSelected.value = brand.get(random).name
        getAllComponentsByBrand(_componentNameSelected.value!!)

    }

    fun buscarBoard() {
        viewModelScope.launch {
            db.collection("Boards")
                .get()
                .addOnSuccessListener { documents ->
                    val boards = mutableListOf<Board>()
                    documents.forEach { document ->
                        val board = document.toObject(Board::class.java)
                        boards.add(board)
                    }
                    _ListBoards.value = boards
                }
        }
    }

    fun buscarCPU() {
        viewModelScope.launch {
            db.collection("CPU").get()
                .addOnSuccessListener { documents ->

                    val cpuList = mutableListOf<CPU>()

                    documents.forEach { document ->
                        val newCPU = document.toObject(CPU::class.java)
                        cpuList.add(newCPU)
                    }

                    _cpus.value = cpuList
                }
        }

    }

    fun getCPUWithLimit(limit: Long) {
        viewModelScope.launch {
            db.collection("CPU").limit(limit.toLong()).get()
                .addOnSuccessListener { documents ->
                    val processors = mutableListOf<CPU>()
                    documents.forEach { document ->
                        val cpu: CPU = document.toObject(CPU::class.java)
                        processors.add(cpu)
                    }
                    _cpus.value = processors
                }
        }
    }

    fun buscarRam() {
        viewModelScope.launch {
            db.collection("Ram")
                .get()
                .addOnSuccessListener { documents ->
                    val listram = mutableListOf<RAM>()
                    documents.forEach { document ->
                        val ram = document.toObject(RAM::class.java)

                        listram.add(ram)
                    }
                    _ListRAM.value = listram
                }
        }
    }

    fun buscarSSD() {
        viewModelScope.launch {
            val docRef = db.collection("Storage")
                .get().addOnSuccessListener { document ->
                    val list = mutableListOf<storage>()
                    document.forEach { document ->
                        val ssd = document.toObject<storage>(storage::class.java)
                        list.add(ssd)
                    }
                    _ssdList.value = list
                }
        }
    }

    fun buscarGraphic() {
        viewModelScope.launch {
            db.collection("Graphics")
                .get().addOnSuccessListener { documents ->
                    val list = mutableListOf<Graphic>()
                    documents.forEach { document ->
                        val graphic = document.toObject(Graphic::class.java)
                        list.add(graphic)
                    }
                    _graphicList.value = list
                }
        }
    }


    fun getGraphicsByPower(power: Long) {
        viewModelScope.launch {
            db.collection("Graphics")
                .orderBy("rendimientoMinimo", Query.Direction.DESCENDING).limit(power)
                .get().addOnSuccessListener { documents ->
                    val list = mutableListOf<Graphic>()
                    documents.forEach { document ->
                        val graphic = document.toObject(Graphic::class.java)
                        list.add(graphic)
                    }
                    _graphicList.value = list
                }

        }
    }


    fun getBoardsByBrand(brand: String) {

        db.collection("Boards").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val board: Board = document.toObject(Board::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            board.nombre,
                            board.urlImagen,
                            board.precio,
                            component = Component.Board.name
                        )
                    )
                }

            }
    }

    fun getGraphicsbyBrand(brand: String) {
        db.collection("Graphics").whereEqualTo("ensamblador", brand).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val graphic: Graphic = document.toObject(Graphic::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            graphic.nombre,
                            graphic.imagen,
                            graphic.precio,
                            Component.Graphic.name
                        )
                    )
                }
            }
    }

    fun getPowerSupplyByBrand(brand: String) {
        db.collection("Fuente").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val psu: fuente = document.toObject(fuente::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            psu.nombre, psu.imagen, psu.precio,
                            component = Component.PSU.name
                        )
                    )
                }
            }
    }

    fun getCpuByBrand(brand: String) {
        db.collection("CPU").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val cpu: CPU = document.toObject(CPU::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            cpu.nombre, cpu.imagen, cpu.precio
                            , component = Component.CPU.name
                        )
                    )
                }
            }
    }

    fun getRamByBrand(brand: String) {
        db.collection("Ram").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val ram: RAM = document.toObject(RAM::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            ram.nombre, ram.imagen, ram.precio,
                            Component.RAM.name
                        )
                    )

                }
            }
    }

    fun getStorageByBrand(brand: String) {
        db.collection("Storage").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val storage: storage = document.toObject(storage::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            storage.nombre, storage.imagen, storage.precio,
                            Component.Storage.name
                        )
                    )
                }
            }
    }

    fun getCaseByBrand(brand: String) {

        db.collection("Case").whereEqualTo("marca", brand)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val psu: fuente = document.toObject(fuente::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            psu.nombre, psu.imagen, psu.precio,
                            Component.Case.name
                        )
                    )
                }
            }
    }

    fun getBoardsByLimit(limit: Long) {

        db.collection("Boards").limit(limit.toLong()).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val board: Board = document.toObject(Board::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            board.nombre,
                            board.urlImagen,
                            board.precio,
                            Component.Board.name
                        )
                    )
                }

            }
    }

    fun getGraphicsByLimit(limit: Long) {
        db.collection("Graphics").limit( limit).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val graphic: Graphic = document.toObject(Graphic::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            graphic.nombre,
                            graphic.imagen,
                            graphic.precio,
                            Component.Graphic.name
                        )
                    )
                }
            }
    }

    fun getPowerSupplyByLimit(limit: Long) {
        db.collection("Fuente").limit(limit).get()
            .addOnSuccessListener { documents ->

                documents.forEach { document ->
                    val psu: fuente = document.toObject(fuente::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            psu.nombre, psu.imagen, psu.precio,
                            Component.PSU.name
                        )
                    )
                }
            }
    }

    fun getCpuByLimit(limit: Long) {
        db.collection("CPU").limit(limit).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val cpu: CPU = document.toObject(CPU::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            cpu.nombre, cpu.imagen, cpu.precio,
                            Component.CPU.name
                        )
                    )
                }
            }



    }

    fun getRamByLimit(limit: Long) {
        db.collection("Ram").limit(limit).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val ram: RAM = document.toObject(RAM::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            ram.nombre, ram.imagen, ram.precio,
                            Component.RAM.name
                        )
                    )

                }
            }
    }

    fun getStorageByLimit(limit: Long) {
        db.collection("Storage").limit( limit).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val storage: storage = document.toObject(storage::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            storage.nombre, storage.imagen, storage.precio,
                            Component.Storage.name
                        )
                    )
                }
            }
    }

    fun getCaseByLimit(limit: Long) {

        db.collection("Case").limit( limit )
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val psu: fuente = document.toObject(fuente::class.java)
                    _componentsWithLimit.value?.add(
                        ComponentDescription(
                            psu.nombre, psu.imagen, psu.precio,
                            Component.Case.name
                        )
                    )
                }
            }
    }

    fun getAllComponentsByBrand(brand: String) {
        viewModelScope.launch {
            getCpuByBrand(brand)
            getBoardsByBrand(brand)
            getRamByBrand(brand)
            getGraphicsbyBrand(brand)
            getPowerSupplyByBrand(brand)
            getStorageByBrand(brand)
            getCaseByBrand(brand)

        }
    }

    fun getAllComponentsByLimit(limit: Long){
        getCpuByLimit(limit)
        getBoardsByLimit(limit)
        getRamByLimit(limit)
        getGraphicsByLimit(limit)
        getPowerSupplyByLimit(limit)
        getStorageByLimit(limit)
        getCaseByLimit(limit)
    }

    fun getAllComponentsByPrice(brand: String) {
        db.collection("Case").whereEqualTo("marca", brand).get()
            .addOnSuccessListener { documents ->
                documents.forEach { document ->
                    val psu: fuente = document.toObject(fuente::class.java)
                    _componentsDescription.value?.add(
                        ComponentDescription(
                            psu.nombre, psu.imagen, psu.precio,
                            Component.Case.name
                        )
                    )
                }
            }
    }


    private val _component = MutableLiveData<CPU?>()
    val component: LiveData<CPU?> get() = _component

    private val _Board = MutableLiveData<Board?>()
    val Boards: LiveData<Board?> get() = _Board

    private val _ram = MutableLiveData<RAM?>()
    val ram: LiveData<RAM?> get() = _ram

    private val _storagePc = MutableLiveData<storage?>()
    val storagePc: LiveData<storage?> get() = _storagePc

    private val _graphic = MutableLiveData<Graphic?>()
    val graphic: LiveData<Graphic?> = _graphic

    private val _chasis = MutableLiveData<Caja?>()
    val chasis: LiveData<Caja?> = _chasis

    private val _psuComponent = MutableLiveData<fuente?>()
    val psuComponent: LiveData<fuente?> = _psuComponent

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
    val graphicOptions: LiveData<Boolean> = _graphicsOptions


    val placaBase: LiveData<Boolean> = _placaBase

    private val _RamPc = MutableLiveData<Boolean>(false)
    val RamPc: LiveData<Boolean> = _RamPc


    private val _almacenamiento = MutableLiveData<Boolean>(false)
    val almacenamiento: LiveData<Boolean> = _almacenamiento


    private val _tarjeta = MutableLiveData<Boolean>(false)
    val tarjeta: LiveData<Boolean> = _tarjeta


    private val _caja = MutableLiveData<Boolean>(false)
    val caja: LiveData<Boolean> = _caja

    private val _psu = MutableLiveData<Boolean>(false)
    val psu: LiveData<Boolean> = _psu

    private val _FuenteAlimentacion = MutableLiveData<Boolean>(false)
    val FuenteAlimentacion: LiveData<Boolean> = _FuenteAlimentacion


    //borrar componente y guardar componente

    fun onComponentSelected(){

    }


    fun guardarPsu(fuente: fuente) {
        _psuComponent.value = fuente

    }

    fun borrarPsu() {
        _psuComponent.value = null
    }

    fun guardarCaja(chasis: Caja) {
        _chasis.value = chasis
    }

    fun borrarCaja() {
        _chasis.value = null
    }

    fun guardarGrafica(grafica: Graphic) {
        _graphic.value = grafica
    }

    fun borrarGrafica() {
        _graphic.value = null
    }


    fun guardarAlmacenamiento(storage: storage) {
        _storagePc.value = storage
    }

    fun borrarAlmacenamiento() {
        _storagePc.value = null
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
        _RamPc.value = true
    }

    fun lockRAM() {
        _RamPc.value = false
    }

    fun unlockStorage() {
        _almacenamiento.value = true
    }

    fun lockStorage() {
        _almacenamiento.value = false
    }

    fun unlockGraphic() {
        _tarjeta.value = true
    }

    fun lockGraphic() {
        _tarjeta.value = false
    }

    fun unlockCase() {
        _caja.value = true
    }

    fun lockCase() {
        _caja.value = false
    }

    fun unlockPsu() {
        _psu.value = true
    }

    fun lockPsu() {
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

    fun lockGraphicOptions() {
        _graphicsOptions.value = true
    }

    fun unlockGraphicOptions() {
        _graphicsOptions.value = false
    }

    //Componente que seleccionamos
    fun cambiarComponente(component: Int) {
        _componentSeleccionado.value = component
    }

}


