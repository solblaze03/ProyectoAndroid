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

val StringsValenciano = Valenciano(
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
    crear = "Crear un compte",
    home = "Inici",
    build = "Acoblar",
    perfil = "Perfil",
    tituloBuild = "Tria els components del teu ordinador",
    procesador = "Processador",
    placabase = "Placa Base",
    Almacenamiento = "Emmagatzematge",
    tarjeta = "Targeta Gràfica",
    caja = "Caixa de l'ordinador",
    fuente = "Font d'alimentació"
)
