package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var btnExplorarLibros: Button
    lateinit var btnFavoritos: Button
    lateinit var btnYaLeidos: Button
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        saludarUsuario()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        btnExplorarLibros = findViewById(R.id.btnExplorarLibros)
        btnFavoritos = findViewById(R.id.btnFavoritos)
        btnYaLeidos = findViewById(R.id.btnYaLeidos)

        btnExplorarLibros.setOnClickListener {
            val mensaje = "Botón Explorar Libros"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnFavoritos.setOnClickListener{
            val mensaje = "Botón Favoritos"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        btnYaLeidos.setOnClickListener {
            val mensaje = "Botón Ya Leídos"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_mi_biblioteca) {
            val intent = Intent(this, MiBibliotecaActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.item_cerrar_sesion) {
            val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
            preferencias.edit().clear().apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saludarUsuario() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val nombreUsuario = bundle?.getString(resources.getString(R.string.nombre_usuario))
            Toast.makeText(this, "Bienvenido/a $nombreUsuario", Toast.LENGTH_LONG).show()
        }
    }
}