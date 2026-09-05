package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText

class EnviarMensajeActivity : AppCompatActivity() {

    private lateinit var btnAtras: ImageView
    private lateinit var etMensaje: TextInputEditText
    private lateinit var btnCancelar: MaterialButton
    private lateinit var btnEnviar: MaterialButton
    private lateinit var cardEstado: MaterialCardView
    private lateinit var tvEstado: TextView

    private lateinit var launcher: androidx.activity.result.ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_mensaje)

        btnAtras = findViewById(R.id.btnAtras)
        etMensaje = findViewById(R.id.etMensaje)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnEnviar = findViewById(R.id.btnEnviar)
        cardEstado = findViewById(R.id.cardEstado)
        tvEstado = findViewById(R.id.tvEstado)

        btnAtras.setOnClickListener { finish() }

        // Cancelar descarta el mensaje escrito y regresa a la pantalla anterior
        btnCancelar.setOnClickListener {
            etMensaje.text?.clear()
            finish()
        }

        // Registra el "escucha" que espera la respuesta de Activity2
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val estado = result.data?.getStringExtra("ESTADO")
                tvEstado.text = "Estado: $estado"
                cardEstado.visibility = View.VISIBLE
            }
        }

        // Al presionar Enviar, mandamos el texto a Activity2
        btnEnviar.setOnClickListener {
            val mensaje = etMensaje.text.toString()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("MENSAJE", mensaje)
            launcher.launch(intent)
        }
    }
}