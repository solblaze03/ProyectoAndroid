package com.example.practicarlogin.`view-model`

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicarlogin.models.CPU
import com.example.practicarlogin.models.Componente
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {


    private val db = FirebaseFirestore.getInstance()

    private val _componenteSearch = MutableLiveData<Componente>()
    val componenteSearch: LiveData<Componente> = _componenteSearch

    private val _cpu = MutableLiveData<List<CPU>>()
    val cpu: LiveData<List<CPU>> = _cpu

    private val _cpuFilter = MutableLiveData<List<CPU>>()
    val cpuFilter: LiveData<List<CPU>> = _cpuFilter

    private val _marcaSeleccionada = MutableLiveData<String>()
    val marcaSeleccionada : LiveData<String> = _marcaSeleccionada

    private val _socketSeleccionado = MutableLiveData<String>()
    val socketSeleccionado: LiveData<String> = _socketSeleccionado

    private val _maxSelect = MutableLiveData<Boolean>(false)
    val maxSelect : LiveData<Boolean> = _maxSelect

    private val _ordenado = MutableLiveData<Boolean?>()
    val ordenado : LiveData<Boolean?> = _ordenado

    private val _rango = MutableLiveData<ClosedFloatingPointRange<Float>>()
    val rango : LiveData<ClosedFloatingPointRange<Float>> = _rango






    fun setOrdenado(ordenar : Boolean?){
        _ordenado.value = ordenar
        filtrarCPU()

    }

    fun setRango(rango: ClosedFloatingPointRange<Float>){
        _rango.value = rango
        filtrarCPU()
    }


    fun ElegirComponente(componente: Componente) {
        _componenteSearch.value = componente
    }

    fun elegirMarca(marca:String){
        _marcaSeleccionada.value = marca
        filtrarCPU()
    }

    fun elegirSocket(socket: String) {
        _socketSeleccionado.value = socket
        filtrarCPU()
    }


    init {
        viewModelScope.launch {
            initCPU(emptyList())
            buscarCPU()
            setRango(0f..50000f)

        }
    }

    fun buscarCPU() {
        viewModelScope.launch {
            val docRef = db.collection("CPU").get()
                .addOnSuccessListener { documents ->
                    val cpuList = mutableListOf<CPU>()
                    documents.forEach { document ->
                        val processor = document.toObject(CPU::class.java)
                        cpuList.add(processor)
                    }
                    _cpu.value = cpuList
                    filtrarCPU()
                }
        }
    }

    fun initCPU(list: List<CPU>) {
        _cpuFilter.value = list
    }

    fun filtrarCPU() {

        val marca =  _marcaSeleccionada.value.orEmpty()
        val socket = _socketSeleccionado.value.orEmpty()

        val ordenado = _ordenado.value ?: false

        val rangoPC : ClosedFloatingPointRange<Float> = _rango.value ?: 0.0f..50.000f


        val cpuList = _cpu.value ?: emptyList()
        var sortedList = emptyList<CPU>()


        val filteredList = cpuList.filter { cpu ->
            (marca.isEmpty() || cpu.marca.contains(marca, ignoreCase = true)) &&
                    (socket.isEmpty() || cpu.socket.contains(socket, ignoreCase = true))

        }
        Log.i("filtro", _ordenado.value.toString())



        val rangeCPU = filteredList.filter { cpu ->
            cpu.precio > rangoPC.start.toInt() && cpu.precio < rangoPC.endInclusive.toInt()
        }


        if(_ordenado.value ?:false){



            sortedList = rangeCPU.sortedBy { it.precio }
            _cpuFilter.value = sortedList
        }else if(!ordenado){
            _cpuFilter.value = rangeCPU.sortedByDescending { it.precio }
        }else{
            _cpuFilter.value = rangeCPU
        }



    }

}