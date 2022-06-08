package com.example.regalum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.regalum.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //infla o layout
        setContentView(binding.root) // Se inicializa el layout
        this.supportActionBar?.hide() //Ocultamos la barra de acciones
    }
}