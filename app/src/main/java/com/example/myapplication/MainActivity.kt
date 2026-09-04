package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var tvSaludo: TextView
    private lateinit var ivIconoSaludo: ImageView
    private lateinit var cardEnviarMensaje: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSaludo = findViewById(R.id.tvSaludo)
        ivIconoSaludo = findViewById(R.id.ivIconoSaludo)
        cardEnviarMensaje = findViewById(R.id.cardEnviarMensaje)

        mostrarSaludoSegunHora()

        // Única actividad activa del listado: Enviar mensaje
        cardEnviarMensaje.setOnClickListener {
            startActivity(Intent(this, EnviarMensajeActivity::class.java))
        }
    }

    /**
     * Muestra "Buenos días", "Buenas tardes" o "Buenas noches" según la hora
     * del dispositivo, junto con un ícono de sol o luna a juego.
     */
    private fun mostrarSaludoSegunHora() {
        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val saludo = when (hora) {
            in 5..11 -> "Buenos días"
            in 12..18 -> "Buenas tardes"
            else -> "Buenas noches"
        }

        val esDeDia = hora in 6..18
        ivIconoSaludo.setImageResource(if (esDeDia) R.drawable.ic_sun else R.drawable.ic_moon)

        tvSaludo.text = saludo
    }
}