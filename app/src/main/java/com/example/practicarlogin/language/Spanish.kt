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
    override val fuente: String,
    override val marca: String,
    override val nucleos: String,
    override val zocalo: String,
    override val hilos: String,
    override val vermas: String,
    override val agregar: String,
    override val buscar: String, override val reloj: String, override val litografia: String
) : languages

val StringsSpanish = Spanish(
    //login
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
    crear = "Crear una cuenta",
    //bottom navigation
    home = "Inicio",
    build = "Ensamblar",
    perfil = "Perfil",
    //bottom build
    tituloBuild = "Elige los componentes de tu ordenador",
    procesador = "Procesador",
    placabase = "Placa base",
    Almacenamiento = "Almacenamiento",
    tarjeta = "Tarjeta gráfica",
    caja = "Caja del ordenador",
    fuente = "Fuente de alimentación",
    //pantalla cards
    marca = "Marca",
    nucleos = "Núcleos",
    zocalo = "Zócalo",
    hilos = "Hilos",
    vermas = "Ver más",
    agregar = "Agregar",
    buscar = "Buscar",
    reloj = "Reloj",
    litografia = "Litografía"
)