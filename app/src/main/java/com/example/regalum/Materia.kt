package com.example.regalum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.regalum.databinding.FragmentMateriaBinding

class Materia : Fragment() {
    private var _binding: FragmentMateriaBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: String

    private lateinit var clave: String
    private lateinit var nm: String
    private lateinit var dia: String
    private lateinit var hi: String
    private lateinit var hf: String

    private var NOMBRE_ARCHIVO = "materia.txt"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMateriaBinding.inflate(inflater, container, false)
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
        clave = edtClaveM.text.toString()
        nm = edtNMat.text.toString()
        dia = edtDiaM.text.toString()
        hi = edtHInicial.text.toString()
        hf = edtHFinal.text.toString()
    }

    //Enviar datos al archivo de texto
    private fun enviarDatos() {
        data = "$clave, $nm, $dia, $hi, $hf\n" //Se crea el set de datos
        binding.tvResultados.append(guardarDatos(data)) //Se guarda el set en el archivo
    }

    //Validar que cada elemento no este vacio
    private fun validarDatos(): Boolean {
        return clave.isEmpty() || nm.isEmpty()
                || dia.isEmpty() || hi.isEmpty()
                || hf.isEmpty()
    }

    //Mensaje de informacion 
    private fun message(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

}