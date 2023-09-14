package com.example.gestionfinanzas.Modelos

class Gasto {
    var idCuenta: Int
    var monto:Double
    var fecha: String
    var categoria: String
    var descripcion: String

    constructor(
        idCuenta: Int,
        monto:Double,
        fecha: String,
        categoria: String,
        descripcion: String

    ) {
        this.idCuenta = idCuenta
        this.monto = monto
        this.fecha = fecha
        this.categoria = categoria
        this.descripcion = descripcion
    }
}