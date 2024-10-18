package com.example.worldbook

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class describirLibro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_describir_libro)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

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

    }
}