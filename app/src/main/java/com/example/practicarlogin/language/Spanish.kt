package com.example.practicarlogin.language

data class Spanish(
    override val appName: String,
    override val inicioSesion: String,
    override val nameUser: String,
    override val promptUser: String,
    override val password: String,
    override val olvPassword: String,
    override val inPass: String,
    override val inicioAut: String,
    override val oIniciar: String,
    override val google: String,
    override val crear: String
) : languages

val StringsSpanish = Spanish(
    appName = "PracticarLogin",
    inicioSesion = "Iniciar Sesión",
    nameUser = "Nombre de usuario",
    promptUser = "Introduce tu nombre de usuario",
    password = "Contraseña",
    olvPassword = "Olvidaste la contraseña",
    inPass = "Introduce tu contraseña",
    inicioAut = "Inicio automático",
    oIniciar = "O iniciar sesión con",
    google = "Continuar con Google",
    crear = "Crear una cuenta")