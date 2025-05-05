package com.example.practicarlogin.`view-model`

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){


    private val _user = MutableLiveData<String>()
    val user : LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _registrarUser = MutableLiveData<String>()
    val registrarUser : LiveData<String> = _registrarUser

    private val _registrarPass = MutableLiveData<String>()
    val registrarPass : LiveData<String> = _registrarPass

    private val _verificado = MutableLiveData<Boolean>()
    val verificado : LiveData<Boolean> = _verificado

    private val _verificadoRegistro = MutableLiveData<Boolean>()
    val verificadoRegistro : LiveData<Boolean> = _verificadoRegistro

    val auth: FirebaseAuth = Firebase.auth

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private val _CreateState = MutableLiveData<LoginState>()
    val CreateState: LiveData<LoginState> = _CreateState

    private val _security = MutableLiveData<Int>(0)
    val security : LiveData<Int> = _security

  

    fun inicioSesion(username : String, password : String){
        _user.value = username
        _password.value = password
        _verificado.value = Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length > 8
        _loginState.value = LoginState.Idle
    }
    fun registroSesion(username : String, password : String){
        _registrarUser.value = username
        _registrarPass.value = password
        _verificadoRegistro.value = Patterns.EMAIL_ADDRESS.matcher(username).matches() && password.length >= 6

    }



    fun verificar(username: String, password: String, context: Context, function: () -> Unit){
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(username, password).addOnSuccessListener {
                function()
                _loginState.value = LoginState.Success
            }.addOnFailureListener {
                _loginState.value = LoginState.Error("Error")
            }
        }
    }

    fun CrearCuenta(username: String, password: String,cambioPantalla: ()-> Unit){
        viewModelScope.launch {
            Log.i("crear", "llllllll")
            auth.createUserWithEmailAndPassword(username, password).addOnSuccessListener {
                cambioPantalla()
            }.addOnFailureListener {
                _CreateState.value = LoginState.Error("")
            }
        }
    }

    fun setSecurity(valor : Int){
        _security.value = valor
    }


}