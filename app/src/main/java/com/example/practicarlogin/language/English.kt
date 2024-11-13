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
    override val crear: String
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
    crear = "Create an account")