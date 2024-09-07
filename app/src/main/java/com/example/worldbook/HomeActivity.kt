package com.example.worldbook

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var btnExplorarLibros: Button
    lateinit var btnMiBiblioteca: Button
    lateinit var btnFavoritos: Button
    lateinit var btnYaLeidos: Button
    lateinit var btnCerrarSesión: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnExplorarLibros = findViewById(R.id.btnExplorarLibros)
        btnMiBiblioteca = findViewById(R.id.btnMiBiblioteca)
        btnFavoritos = findViewById(R.id.btnFavoritos)
        btnYaLeidos = findViewById(R.id.btnYaLeidos)
        btnCerrarSesión = findViewById(R.id.btnCerrarSesión)

        btnExplorarLibros.setOnClickListener{
            var mensaje = "Botón Explorar Libros"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnMiBiblioteca.setOnClickListener{
            var mensaje = "Botón Mi Biblioteca"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnFavoritos.setOnClickListener{
            var mensaje = "Botón Favoritos"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnYaLeidos.setOnClickListener{
            var mensaje = "Botón Ya Leídos"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnCerrarSesión.setOnClickListener{
            var mensaje = "Botón Cerrar Sesión"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}