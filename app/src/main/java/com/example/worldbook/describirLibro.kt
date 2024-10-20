package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class describirLibro : AppCompatActivity() {
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_describir_libro)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        // Inicializar y configurar el Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Opciones"


        // Recuperar los datos del Intent
        val title = intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val coverUrl = intent.getStringExtra("coverUrl")

        val publisher = intent.getStringExtra("publisher")
        val publishedDate = intent.getStringExtra("publishedDate")
        val description = intent.getStringExtra("description")

        // Actualizar las vistas con los datos
        findViewById<TextView>(R.id.bookTitleTextView).text = title
        findViewById<TextView>(R.id.bookAuthorTextView).text = author
        findViewById<TextView>(R.id.bookPublisherTextView).text = publisher
        findViewById<TextView>(R.id.bookPublishedDateTextView).text = publishedDate
        findViewById<TextView>(R.id.bookDescriptionTextView).text = description

        // Usar Glide (u otra librería) para cargar la imagen de la portada
        val imageView = findViewById<ImageView>(R.id.bookCoverImageView)
        Glide.with(this).load(coverUrl).into(imageView)

        // Configurar el botón para añadir a favoritos
        val btnAniadirFavorito = findViewById<Button>(R.id.btnAniadirFavorito)
        btnAniadirFavorito.setOnClickListener {
            // Crear un BookItem con los datos actuales
            val bookItem = BookItem(
                id = "0", // ID ficticio, si tienes un ID real lo puedes usar
                volumeInfo = VolumeInfo(
                    title = title ?: "",
                    authors = listOf(author ?: ""),
                    publisher = publisher ?: "",
                    publishedDate = publishedDate ?: "",
                    description = description ?: "",
                    imageLinks = ImageLinks(coverUrl ?: "")
                )
            )

            // Añadir a favoritos usando el FavoritosManager
            FavoritosManager.agregarFavorito(bookItem)

            // Mostrar un Toast
            Toast.makeText(this, "Añadido a Favoritos", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_cerrar_sesion -> {
                val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                preferencias.edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}