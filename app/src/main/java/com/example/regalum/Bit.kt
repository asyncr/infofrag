package com.example.regalum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.regalum.databinding.FragmentBitBinding


class Bit : Fragment() {
    private var _binding: FragmentBitBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: String

    private lateinit var fechar: String
    private lateinit var nc: String
    private lateinit var clavem: String
    private var NOMBRE_ARCHIVO = "bitacora.txt"
    private var MATERIA_ARCHIVO = "materia.txt"
    private var ESTUDIANTE_ARCHIVO = "estudiante.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBitBinding.inflate(inflater, container, false)
        file(NOMBRE_ARCHIVO) //Llamamos a la funcion file para leer el archivo
        with(binding) {
            if (!requestPermission(binding.root.context)) { //Si se aceptan los permisos se lee el archivo
                readFile().let {
                    tvResultados.text = it
                } //Se lee el archivo y se muestra en el TextView
                message("Cargando datos")
            }
            btnRegistrar.setOnClickListener {
                if (!requestPermission(binding.root.context)) { //Si se aceptan los permisos
                    obtenerDatos() //Se obtienen los datos
                    //Se validan los datos ingresados
                    if (!validarDatos()) enviarDatos() else message("Ingresa todos los datos")
                }
            }
        }
        return binding.root
    }

    //Obtener datos de los elementos de la vista
    private fun obtenerDatos() = with(binding) {
        fechar = edtFechaR.text.toString()
        nc = edtNControl.text.toString()
        clavem = edtClaveM.text.toString()
    }

    //Enviar datos al archivo de texto
    private fun enviarDatos() {
        file(ESTUDIANTE_ARCHIVO)
        var existNC = validar(nc)
        file(MATERIA_ARCHIVO)
        var existClave = validar(clavem)
        if (!existNC) message("No existe el estudiante")
        if (!existClave) message("No existe la materia")
        if (existNC && existClave) {
            message("Registro exitoso")
            file(NOMBRE_ARCHIVO)
            data = "$fechar,$nc,$clavem" //Se crea el set de datos
            binding.tvResultados.append(guardarDatos(data)) //Se guarda el set en el archivo
        }
    }

    //Validar que cada elemento no este vacio
    private fun validarDatos(): Boolean {
        return nc.isEmpty() || fechar.isEmpty() || clavem.isEmpty()
    }

    //Mensaje de informac
    private fun message(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

    //Sumar 2 numeros y retornar el resultado

}