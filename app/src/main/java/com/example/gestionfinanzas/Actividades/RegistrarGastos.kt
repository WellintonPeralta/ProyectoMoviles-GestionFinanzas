package com.example.gestionfinanzas.Actividades

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.gestionfinanzas.Modelos.BaseDeDatosFirestore
import com.example.gestionfinanzas.Modelos.Gasto
import com.example.gestionfinanzas.R

class RegistrarGastos : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_gastos)
        val btn_guardar_gasto = findViewById<Button>(R.id.btn_guardar_ingreso)

        btn_guardar_gasto.setOnClickListener {
            guardarGasto()
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

    fun guardarGasto(){
        val monto = findViewById<EditText>(R.id.txt_monto_gasto)
        val fecha = findViewById<EditText>(R.id.txt_fecha_gasto)
        val categoria = findViewById<EditText>(R.id.txt_categoria_gasto)
        val descripcion = findViewById<EditText>(R.id.txt_descripcion_gasto)

        if (
            monto.text.isNotEmpty()&&
            fecha.text.isNotEmpty() &&
            categoria.text.isNotEmpty() &&
            descripcion.text.isNotEmpty()
        ) {
            BaseDeDatosFirestore.crearGasto(
                Gasto(
                1,
                monto.text.toString().toDouble(),
                fecha.text.toString(),
                categoria.text.toString(),
                descripcion.text.toString(),
            )
            )
            finish()


        }
    }
}