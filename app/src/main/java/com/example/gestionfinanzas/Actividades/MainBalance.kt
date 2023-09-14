package com.example.gestionfinanzas.Actividades

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gestionfinanzas.R

class MainBalance : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_balance)

        val btn_agregarIngreso = findViewById<Button>(R.id.btn_nuevo_ingreso)
        val btn_agregarGasto = findViewById<Button>(R.id.btn_nuevo_gasto)
        val btn_detallesIngreso = findViewById<Button>(R.id.btn_detalles_ingresos)
        val btn_detallesGasto = findViewById<Button>(R.id.btn_detalles_gastos)

        btn_agregarIngreso.setOnClickListener {
            irActividad(RegistrarIngresos::class.java)
        }
        btn_agregarGasto.setOnClickListener {
            irActividad(RegistrarGastos::class.java)
        }
        btn_detallesIngreso.setOnClickListener {
            irActividad(DetalleIngresos::class.java)
        }
        btn_detallesGasto.setOnClickListener {
            irActividad(DetalleGastos::class.java)
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