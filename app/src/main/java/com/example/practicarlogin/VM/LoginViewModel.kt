package com.example.practicarlogin.VM

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    private val nameuser = "Nala"
    private val userpassword = "2003"

    private val _user = MutableLiveData<String>()
    val user : LiveData<String> = _user

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    fun inicioSesion(username : String, password : String){
        _user.value = username
        _password.value = password

    }


    fun verificar(username: String, password: String, context: Context, function: () -> Unit){
        if(_user.value == nameuser && _password.value == userpassword){
            function()

        }else{

            Toast.makeText(context,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show()
        }
    }


}