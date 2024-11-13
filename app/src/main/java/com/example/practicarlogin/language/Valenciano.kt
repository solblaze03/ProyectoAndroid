package com.example.practicarlogin.language

data class Valenciano(
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

val StringsValenciano = Spanish(
    appName = "PracticarLogin",
    inicioSesion = "Iniciar Sessió",
    nameUser = "Nom d'usuari",
    promptUser = "Introduïx el teu nom d'usuari",
    password = "contrasenya",
    olvPassword = "Heu oblidat la contrasenya?",
    inPass = "Introduïx la teua contrasenya",
    inicioAut = "Inici automàtic",
    oIniciar = "O iniciar sessió amb",
    google = "Continuar amb Google",
    crear = "Crear un compte")
