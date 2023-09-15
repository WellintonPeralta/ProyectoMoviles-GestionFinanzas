package com.example.gestionfinanzas.Modelos

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class BaseDeDatosFirestore {
    val arreglo: ArrayList<Persona> = arrayListOf()
    private val db = FirebaseFirestore.getInstance()

    companion object {

        @SuppressLint("StaticFieldLeak")
        private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        // Métodos para Persona
        fun signInWithEmailAndPassword(email: String, password: String, callback: (Boolean) -> Unit) {
            val auth = FirebaseAuth.getInstance()

            // Intenta iniciar sesión con el correo y la contraseña proporcionados
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // La autenticación fue exitosa
                        callback(true)
                    } else {
                        // La autenticación falló
                        val exception = task.exception
                        if (exception is FirebaseAuthInvalidUserException) {
                            // El correo no está registrado
                            callback(false)
                        } else if (exception is FirebaseAuthInvalidCredentialsException) {
                            // La contraseña es incorrecta
                            callback(false)
                        } else {
                            // Otra excepción, manejar según sea necesario
                            callback(false)
                        }
                    }
                }
        }

        fun obtenerCuentaPorIdPersona(idPersona: String, callback: (Cuenta?) -> Unit) {
            val db = FirebaseFirestore.getInstance()
            val cuentasRef = db.collection("cuentas") // Reemplaza "cuentas" con el nombre de tu colección en Firestore

            cuentasRef
                .whereEqualTo("idPersona", idPersona)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val document = querySnapshot.documents[0]
                        val saldoTotal = document.getDouble("saldoTotal")
                        val idPersona = document.getString("idPersona")?: ""
                        val totalGastos = document.getDouble("totalGastos")
                        val totalIngresos = document.getDouble("totalIngresos")
                        val fechaCreacion = document.getString("fechaCreacion")

                        if (saldoTotal != null && totalGastos != null && totalIngresos != null && fechaCreacion != null) {
                            val cuenta = Cuenta(document.id, idPersona, fechaCreacion, saldoTotal, totalIngresos, totalGastos)
                            callback(cuenta)
                        } else {
                            callback(null) // Datos incompletos o nulos
                        }
                    } else {
                        callback(null) // No se encontraron documentos con el idPersona proporcionado
                    }
                }
                .addOnFailureListener { e ->
                    callback(null) // Error de consulta
                }
        }

        fun crearIngreso(gasto: Gasto) {
            db.collection("Ingreso")
                .document(gasto.descripcion)
                .set(gasto)
                .addOnSuccessListener { documentReference ->
                    println("Se agrego un ingreso")
                }
                .addOnFailureListener { e ->
                    println("Error al agregar el gasto: $e")
                }
        }

        fun crearGasto(gasto: Gasto) {
            db.collection("Gasto")
                .document(gasto.descripcion)
                .set(gasto)
                .addOnSuccessListener { documentReference ->
                    println("Se agrego un gasto")
                }
                .addOnFailureListener { e ->
                    println("Error al agregar el gasto: $e")
                }
        }


    }
}