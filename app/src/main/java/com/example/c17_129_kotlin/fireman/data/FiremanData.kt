package com.example.c17_129_kotlin.fireman.data

// Clase de datos para representar a un bombero
data class Bombero(
    val id: Int = 0,
    val nombre: String = "",
    val numeroTelefono: String = "",
    val direccion: String = ""
)