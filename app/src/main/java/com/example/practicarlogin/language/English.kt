package com.example.practicarlogin.language

data class English(
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
    override val crear: String,
    override val home: String,
    override val build: String,
    override val perfil: String,
    override val tituloBuild: String,
    override val procesador: String,
    override val placabase: String,
    override val Almacenamiento: String,
    override val tarjeta: String,
    override val caja: String,
    override val fuente: String
) : languages

val StringsEnglish = English(
    appName = "PracticeLogin",
    inicioSesion = "Login",
    nameUser = "User name",
    promptUser = "Enter your username",
    password = "Password",
    olvPassword = "You forgot your password",
    inPass = "Enter your password",
    inicioAut = "Auto start",
    oIniciar = "Or log in with",
    google = "Continue with Google",
    crear = "Create an account",
    home = "Home",
    build = "Build",
    perfil = "Profile",
    tituloBuild = "Choose your computer components",
    procesador = "Processor",
    placabase = "Motherboard",
    Almacenamiento = "Storage",
    tarjeta = "Graphic card",
    caja = "Computer case",
    fuente = "Power supply"
)