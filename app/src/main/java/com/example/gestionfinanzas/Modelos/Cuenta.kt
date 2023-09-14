package com.example.gestionfinanzas.Modelos

class Cuenta {
    var idCuenta: Int
    var idPersona: Int
    var fechaCreacion: String
    var saldoTotal:Double
    var totalIngresos:Double
    var totalGastos:Double

    constructor(
        idCuenta: Int,
        idPersona: Int,
        fechaCreacion: String,
        saldoTotal:Double,
        totalIngresos:Double,
        totalGastos:Double,

    ) {
        this.idCuenta = idCuenta
        this.idPersona = idPersona
        this.fechaCreacion = fechaCreacion
        this.saldoTotal = saldoTotal
        this.totalIngresos = totalIngresos
        this.totalGastos = totalGastos
    }
}