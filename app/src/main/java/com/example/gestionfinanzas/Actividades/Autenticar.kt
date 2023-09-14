package com.example.gestionfinanzas.Actividades

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.gestionfinanzas.Modelos.BaseDeDatosFirestore
import com.example.gestionfinanzas.Modelos.Persona
import com.example.gestionfinanzas.R

class Autenticar : AppCompatActivity() {
    private var usuario: Persona? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticar)

        // login

        val btnAutenticar = findViewById<Button>(R.id.btn_iniciar_sesion)

        btnAutenticar.setOnClickListener {
            val correo = findViewById<EditText>(R.id.txt_correo).text.toString()
            val contrasenia = findViewById<EditText>(R.id.txt_contrasenia).text.toString()

            val intent = Intent(this, MainBalance::class.java)
            intent.putExtra("idPersona", "cGIQJrzC86BQeGod1k80")
            startActivity(intent)

            /*
            usuario = BaseDeDatosFirestore.verificarUsuario(correo, contrasenia)
            if (usuario != null){
                val intent = Intent(this, MainBalance::class.java)
                intent.putExtra("idPersona", "cGIQJrzC86BQeGod1k80")
                startActivity(intent)
            }*/
        }


    }

    private fun irActividad(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.putExtra("idPersona", "cGIQJrzC86BQeGod1k80")
        startActivity(intent)
    }
}