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
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val idPersona = document.id
                        val nombre = document.getString("nombre")?: ""
                        val correo = document.getString("correo")?: ""
                        val contrasenia = document.getString("contrasenia")?: ""

                        usuario = Persona(idPersona, nombre, correo, contrasenia)
                    }
                }
                .addOnFailureListener { e ->
                    // Captura y registra la excepción en el logcat
                    Log.e("Auteneticacion", "Error al logear usuario: ${e.message}", e)
                }
            return  usuario
        }


    }
}