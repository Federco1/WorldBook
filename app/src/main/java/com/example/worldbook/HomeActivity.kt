package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var btnExplorarLibros: Button
    lateinit var btnFavoritos: Button
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

        btnExplorarLibros.setOnClickListener {
            /*val mensaje = "Botón Explorar Libros"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()*/
            val intent = Intent(this, CatalogoActivity::class.java)
            startActivity(intent)

        }
        btnFavoritos.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

        val logoFragmento = LogoFragmento()
        supportFragmentManager.beginTransaction()
            .replace(R.id.logo_contenedor, logoFragmento)
            .addToBackStack(null)
            .commit()

        val textoFragmento = TextoFragmento()
        supportFragmentManager.beginTransaction()
            .replace(R.id.texto_contenedor, textoFragmento)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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