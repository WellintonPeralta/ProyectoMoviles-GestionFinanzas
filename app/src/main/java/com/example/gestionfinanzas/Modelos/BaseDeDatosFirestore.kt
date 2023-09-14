package com.example.gestionfinanzas.Modelos

import android.annotation.SuppressLint
import android.util.Log
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
        fun verificarUsuario(correo: String, contrasenia: String): Persona? {
            var usuario: Persona? = null
            val usuarioRef = db.collection("Persona")
                .whereEqualTo("correo", correo)
                .whereEqualTo("contrasenia", contrasenia)

            usuarioRef.get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val document = querySnapshot.documents[0]
                        usuario = document.toObject(Persona::class.java)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("TAG", "Error al verificar usuario: $e")
                }
            return  usuario
        }

        fun obtenerCuentaPorUsuario(idUsuario: String): Cuenta?{
            var cuenta: Cuenta? = null
            val cuentaRef = db.collection("Cuenta")
                .whereEqualTo("idPersona", idUsuario)

            cuentaRef.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val idCuenta = document.id
                        val idPersona = document.getString("idPersona")?: ""
                        val fechaCreacion = document.getString("fechaCreacion")?: ""
                        val saldoTotal = document.getString("saldoTotal")?.toDouble() ?: 0.0
                        val totalIngresos = document.getString("totalIngresos")?.toDouble() ?: 0.0
                        val totalGastos = document.getString("totalGastos")?.toDouble() ?: 0.0

                        cuenta = Cuenta(idCuenta, idPersona, fechaCreacion, saldoTotal, totalIngresos, totalGastos)
                    }
                }
                .addOnFailureListener { e ->
                    // Captura y registra la excepción en el logcat
                    Log.e("Cuenta", "Error al cargar la cuenta del usuario: ${e.message}", e)
                }
            return  cuenta
        }


    }
}