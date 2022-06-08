package com.example.regalum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController as find


class Dash : Fragment() {
    private lateinit var listImg: MutableList<Int>
    private lateinit var listTv: MutableList<Int>
    private lateinit var listCrd: MutableList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash, container, false)
        createList() //Crea de listas,textos y imagenes
        detectedList(view) //Detecta el elemento presionado
        return view //Retorna la vista
    }

    //Crea la lista de imagenes, textos para manejar el evento de click
    private fun createList() {
        this.listCrd = mutableListOf(R.id.crdStudent, R.id.crdMateria, R.id.crdCenter, R.id.crdBit)
        this.listImg = mutableListOf(R.id.imgStudent, R.id.imgMateria, R.id.imgCenter, R.id.imgBit)
        this.listTv = mutableListOf(R.id.tvStudent, R.id.tvMateria, R.id.tvCenter, R.id.tvBit)
    }

    //Funcion que detecta el elemento presionado y envia a la vista correspondiente
    private fun detectedList(view: View) {
        for (index in listImg.indices) { //Recorrendo la lista de imagenes
            val elementImg = view.findViewById<ImageView>(listImg[index]) //Obteniendo el elemento de la lista
            val elementTv = view.findViewById<TextView>(listTv[index]) //Obteniendo el elemento de la lista
            val elementCrd = view.findViewById<CardView>(listCrd[index]) //Obteniendo el elemento de la lista
            elementImg!!.setOnClickListener { redirec(index) } //Agregando el evento de click
            elementTv!!.setOnClickListener { redirec(index) } //Agregando el evento de click
            elementCrd!!.setOnClickListener { redirec(index) } //Agregando el evento de click
        }
    }

    //Funcion que redirecciona a la vista correspondiente
    private fun redirec(index: Int) {
        when (index) {
            0 -> find().navigate(R.id.action_dash_to_estudiante) // Vista de estudiante
            1 -> find().navigate(R.id.action_dash_to_materia) // Vista de profesor
            2 -> find().navigate(R.id.action_dash_to_ccomputo) // Vista de centro de computo
            3 -> find().navigate(R.id.action_dash_to_bit) // Vista de bitacora
        }
    }
}