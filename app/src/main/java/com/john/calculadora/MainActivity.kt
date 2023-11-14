package com.john.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var numero1: Double = 0.0
    private var numero2: Double = 0.0
    private var operacion: String = ""
    private var textoActual: String = ""
    private lateinit var boton : Button

    private lateinit var pantalla: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantalla = findViewById(R.id.txt_pantalla)

    }

    fun onNumeroClick(view: View) {
        boton = view as Button
        if (!(boton.text.matches(Regex("=")))){
            textoActual += boton.text
            actualizarPantalla()
        }
    }

    private fun tomarDatos(){
        var datos = textoActual
        var token : Array<String> = datos.toString().split("[+\\-*/]".toRegex()).toTypedArray()
        numero1 = token[0].toDouble()
        numero2 = token[1].toDouble()
        val coincidencia = "[+\\-/*]".toRegex()
        textoActual.forEach {
            if (it.toString().matches(coincidencia)){
                operacion = it.toString()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun onIgualClick(view: View) {
        tomarDatos()
        // Realizar la operación y mostrar el resultado en la pantalla
        when (operacion) {
            "+" -> pantalla.text = (numero1 + numero2).toString()
            "-" -> pantalla.text = (numero1 - numero2).toString()
            "*" -> pantalla.text = (numero1 * numero2).toString()
            "/" -> {
                if (numero2 != 0.0) {
                    pantalla.text = (numero1 / numero2).toString()
                } else {
                    pantalla.text = "Error"
                }
            }
        }
    }
    fun onLimpiarClick(view: View) {
        limpiarPantalla()
    }
    private fun limpiarPantalla() {
        // Limpiar la pantalla y reiniciar las variables
        pantalla.text = ""
        textoActual = ""
        numero1 = 0.0
        numero2 = 0.0
        operacion = ""
    }

    fun onLimpiarUno(view: View) {
        if (textoActual.isNotEmpty()) {
            textoActual = textoActual.substring(0, textoActual.length - 1)
            actualizarPantalla()
        }
    }

    private fun actualizarPantalla() {
        pantalla.text = textoActual
    }
}