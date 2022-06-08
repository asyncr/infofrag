package com.example.regalum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.regalum.databinding.FragmentCcomputoBinding


class Ccomputo : Fragment() {

    private var _binding: FragmentCcomputoBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: String

    private lateinit var clave: String
    private lateinit var ns: String

    private var NOMBRE_ARCHIVO = "computo.txt"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCcomputoBinding.inflate(inflater, container, false)
        file(NOMBRE_ARCHIVO) //Llamamos a la funcion file para leer el archivo
        with(binding) {
            if (!requestPermission(binding.root.context)) { //Si se aceptan los permisos se lee el archivo
                readFile().let {tvResultados.text = it } //Se lee el archivo y se muestra en el TextView
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
        clave = edtClaveM.text.toString()
        ns = edtNombre.text.toString()
    }

    //Enviar datos al archivo de texto
    private fun enviarDatos() {
        data = "$clave, $ns\n" //Se crea el set de datos
        binding.tvResultados.append(guardarDatos(data)) //Se guarda el set en el archivo
    }

    //Validar que cada elemento no este vacio
    private fun validarDatos(): Boolean {
        return clave.isEmpty() || ns.isEmpty()
    }

    //Mensaje de informacion
    private fun message(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()


}