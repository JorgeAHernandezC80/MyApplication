package com.example.myapplication

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/** Representa una tarea de la agenda del día. */
data class Tarea(val id: Int, val titulo: String, var completada: Boolean)

class AgendaActivity : AppCompatActivity() {

    private lateinit var btnAtras: ImageView
    private lateinit var tvEncabezadoPendientes: TextView
    private lateinit var tvEncabezadoCompletadas: TextView
    private lateinit var contPendientes: LinearLayout
    private lateinit var contCompletadas: LinearLayout

    // Por ahora las tareas viven solo en memoria (se reinician al cerrar la app).
    // Más adelante se podrían guardar con Room o SharedPreferences para que persistan.
    private val tareas = mutableListOf(
        Tarea(1, "Revisar correos", false),
        Tarea(2, "Entregar taller de Programación de Dispositivos Móviles", false),
        Tarea(3, "Reunión con el equipo del proyecto", false),
        Tarea(4, "Actualizar el repositorio en GitHub", true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        btnAtras = findViewById(R.id.btnAtras)
        tvEncabezadoPendientes = findViewById(R.id.tvEncabezadoPendientes)
        tvEncabezadoCompletadas = findViewById(R.id.tvEncabezadoCompletadas)
        contPendientes = findViewById(R.id.contPendientes)
        contCompletadas = findViewById(R.id.contCompletadas)

        btnAtras.setOnClickListener { finish() }

        renderizarTareas()
    }

    /**
     * Vuelve a dibujar las dos listas (pendientes y completadas) a partir
     * del estado actual de [tareas]. Se llama cada vez que una tarea cambia
     * de estado, para que se "mueva" de una sección a la otra.
     */
    private fun renderizarTareas() {
        contPendientes.removeAllViews()
        contCompletadas.removeAllViews()

        val pendientes = tareas.filter { !it.completada }
        val completadas = tareas.filter { it.completada }

        tvEncabezadoPendientes.text = "Pendientes (${pendientes.size})"
        tvEncabezadoCompletadas.text = "Completadas (${completadas.size})"

        pendientes.forEach { tarea -> contPendientes.addView(crearFilaTarea(tarea)) }
        completadas.forEach { tarea -> contCompletadas.addView(crearFilaTarea(tarea)) }
    }

    /**
     * Infla el layout item_tarea.xml y lo llena con los datos de una tarea.
     * El checkbox no cambia de estado directamente al hacer clic: primero
     * se revierte visualmente y se muestra un diálogo de confirmación;
     * solo la respuesta del diálogo decide el estado final de la tarea.
     */
    private fun crearFilaTarea(tarea: Tarea): View {
        val fila = LayoutInflater.from(this).inflate(R.layout.item_tarea, contPendientes, false)

        val checkTarea = fila.findViewById<CheckBox>(R.id.checkTarea)
        val tvTituloTarea = fila.findViewById<TextView>(R.id.tvTituloTarea)

        tvTituloTarea.text = tarea.titulo
        checkTarea.isChecked = tarea.completada
        aplicarEstiloSegunEstado(tvTituloTarea, tarea.completada)

        checkTarea.setOnClickListener {
            // El CheckBox ya cambió su estado visual al hacer clic; lo
            // devolvemos a como estaba mientras esperamos la respuesta.
            checkTarea.isChecked = tarea.completada
            mostrarDialogoConfirmacion(tarea)
        }

        return fila
    }

    /** Pregunta al usuario si la tarea realmente se completó.
     * "Sí" la mueve a Completadas, "No" la deja (o la devuelve) a Pendientes. */
    private fun mostrarDialogoConfirmacion(tarea: Tarea) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar tarea")
            .setMessage("¿La tarea \"${tarea.titulo}\" se completó?")
            .setPositiveButton("Sí") { dialog, _ ->
                tarea.completada = true
                renderizarTareas()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                tarea.completada = false
                renderizarTareas()
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    /** Tacha el texto y lo atenúa cuando la tarea está completada. */
    private fun aplicarEstiloSegunEstado(texto: TextView, completada: Boolean) {
        if (completada) {
            texto.paintFlags = texto.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            texto.setTextColor(getColor(R.color.text_secondary))
        } else {
            texto.paintFlags = texto.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            texto.setTextColor(getColor(R.color.text_primary))
        }
    }
}