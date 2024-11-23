package com.example.practicarlogin.VM

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicarlogin.piezas.CPU

class ComponentViewModel : ViewModel() {
    private val _component = MutableLiveData<CPU>()
    private val component : LiveData<CPU> = _component
}