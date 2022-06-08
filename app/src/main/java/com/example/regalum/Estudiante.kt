package com.example.regalum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.regalum.databinding.FragmentEstudianteBinding

class Estudiante : Fragment() {
    private var _binding: FragmentEstudianteBinding? = null
    private val binding get() = _binding!!
    private lateinit var data: String

    private lateinit var nc: String
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var carrera: String
    private lateinit var semestre: String
    private lateinit var grupoAB: String
    private var NOMBRE_ARCHIVO = "estudiante.txt"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_estudiante, container, false)

        _binding = FragmentEstudianteBinding.inflate(inflater, container, false)
        file(NOMBRE_ARCHIVO) //Llamamos a la funcion file para leer el archivo
        with(binding) {
            if (!requestPermission(binding.root.context)) { //Si se aceptan los permisos se lee el archivo
                readFile().let { tvResultados.text = it } //Se lee el archivo y se muestra en el TextView
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
        nc = edtNC.text.toString()
        nombre = edtNombre.text.toString()
        apellido = edtApellidos.text.toString()
        carrera = cbxCarrera.selectedItem.toString()
        semestre = cbxSemestre.selectedItem.toString()
        grupoAB = cbxGrupo.selectedItem.toString()
    }

    //Enviar datos al archivo de texto
    private fun enviarDatos() {
        data = "$nc, $nombre, $apellido, $carrera, $semestre, $grupoAB\n" //Se crea el set de datos
        binding.tvResultados.append(guardarDatos(data)) //Se guarda el set en el archivo
    }

    //Validar que cada elemento no este vacio
    private fun validarDatos(): Boolean {
        return nc.isEmpty() || nombre.isEmpty()
                || apellido.isEmpty() || carrera.isEmpty()
                || semestre.isEmpty() || grupoAB.isEmpty()
    }

    //Mensaje de informac
    private fun message(str: String) = Toast.makeText(context, str, Toast.LENGTH_SHORT).show()

}