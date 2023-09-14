package com.example.gestionfinanzas.Actividades

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.gestionfinanzas.Modelos.BaseDeDatosFirestore
import com.example.gestionfinanzas.Modelos.Cuenta
import com.example.gestionfinanzas.R

class MainBalance : AppCompatActivity() {
    private var cuenta: Cuenta? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_balance)

        // Recepcion de id del usuario
        val idUsuario = intent.getStringExtra("idPersona")

        if (idUsuario != null){
            //Balance de la cuenta del usuario
            cuenta = BaseDeDatosFirestore.obtenerCuentaPorUsuario(idUsuario)

            if (cuenta != null) {
                findViewById<TextView>(R.id.txt_total_ingreso).text = cuenta!!.totalIngresos.toString()
                findViewById<TextView>(R.id.txt_total_gastos).text = cuenta!!.totalGastos.toString()
                findViewById<TextView>(R.id.txt_total_balance).text = cuenta!!.saldoTotal.toString()
            }

            // Main Activity
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


        }else{
            Log.e("Error", "Usuario no encontrado")
        }
    }


    override fun onStart() {
        super.onStart()
    }

    private fun irActividad(activity: Class<*>, params: Bundle? = null) {
        val intent = Intent(this, activity)
        intent.putExtra("idCuenta", cuenta?.idCuenta)
        startActivity(intent)
    }
}