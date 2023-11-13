package com.john.calculadora

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
    private var datos : Int = 0

    private lateinit var pantalla: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantalla = findViewById(R.id.txt_pantalla)
    }

    fun onNumeroClick(view: View) {
        val boton = view as Button
        if (!(boton.text.matches(Regex("=")))){
            textoActual += boton.text
            actualizarPantalla()
        }

        //tomarDatos()

    }
    fun tomarDatos(){
        val datos = textoActual
        val token : Array<String> = datos.toString().split("").toTypedArray()
        numero1 = token.size.toDouble()
        pantalla.text = numero1.toString()


    }




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