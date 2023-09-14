package com.example.gestionfinanzas

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistrarGastos : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_gastos)
        val btn_guardar_gasto = findViewById<Button>(R.id.btn_guardar_gasto)

        btn_guardar_gasto.setOnClickListener {
            irActividad(MainBalance::class.java)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    private fun irActividad(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        if (params != null) {
            intent.putExtras(params)
        }
        startActivity(intent)
    }
}