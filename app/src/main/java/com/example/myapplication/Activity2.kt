package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {

    private lateinit var tvMensaje: TextView
    private lateinit var btnRecibido: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        tvMensaje = findViewById(R.id.tvMensajeRecibido)
        btnRecibido = findViewById(R.id.btnRecibido)
        btnCancelar = findViewById(R.id.btnCancelar)

        val mensajeRecibido = intent.getStringExtra("MENSAJE")
        tvMensaje.text = mensajeRecibido

        btnRecibido.setOnClickListener {
            enviarResultadoYRegresar("Recibido")
        }

        btnCancelar.setOnClickListener {
            enviarResultadoYRegresar("Cancelado")
        }
    }

    private fun enviarResultadoYRegresar(estado: String) {
        val resultIntent = Intent()
        resultIntent.putExtra("ESTADO", estado)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}