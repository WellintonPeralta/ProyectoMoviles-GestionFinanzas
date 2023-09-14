package com.example.gestionfinanzas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainBalance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_balance)

        val btn_agregarIngreso = findViewById<Button>(R.id.btn_nuevo_ingreso)
        val btn_agregarGasto = findViewById<Button>(R.id.btn_nuevo_gasto)

        btn_agregarIngreso.setOnClickListener {
            irActividad(RegistrarIngresos::class.java)
        }

        btn_agregarGasto.setOnClickListener {
            irActividad(RegistrarGastos::class.java)
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