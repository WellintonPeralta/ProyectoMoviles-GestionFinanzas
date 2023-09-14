package com.example.gestionfinanzas.Modelos

class Persona {
    var idPersona: Int
    var nombre: String
    var correo: String
    var contrasenia: String

    constructor(
        idPersona: Int,
        nombre: String,
        correo: String,
        contrasenia: String

    ) {
        this.idPersona = idPersona
        this.nombre = nombre
        this.correo = correo
        this.contrasenia = contrasenia
    }
}