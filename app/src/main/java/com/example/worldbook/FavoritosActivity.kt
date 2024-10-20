package com.example.worldbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritosActivity : AppCompatActivity() {
    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var favoritosAdapter: LibroAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)


        recyclerFavoritos = findViewById(R.id.recyclerFavoritos)
        recyclerFavoritos.layoutManager = LinearLayoutManager(this)

        // Obtener la lista de libros favoritos desde donde se almacenen (SharedPreferences, etc.)
        val librosFavoritos = FavoritosManager.getFavoritos()

        favoritosAdapter = LibroAdapter(librosFavoritos)
        recyclerFavoritos.adapter = favoritosAdapter
    }
}
