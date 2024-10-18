package com.example.worldbook

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchField: EditText
    private lateinit var searchButton: Button
    private lateinit var adapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        recyclerView = findViewById(R.id.bookList)
        searchField = findViewById(R.id.searchField)
        searchButton = findViewById(R.id.searchButton)

        // Configurar el RecyclerView con un LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Establecer acción del botón de búsqueda
        searchButton.setOnClickListener {
            val query = searchField.text.toString()
            if (query.isNotEmpty()) {
                searchBooks(query) // Hacer la búsqueda
            }
        }

    }

    private fun searchBooks(query: String) {
        val bookApi = RetrofitClient.instance.create(LibroEndpoints::class.java)
        val call = bookApi.searchBooks(query)


        call.enqueue(object : Callback<BookResponse> {
            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                if (response.isSuccessful) {
                    val libros = response.body()?.items ?: emptyList()
                    Log.d("MainActivity", "Libros encontrados: ${libros.size}")

                    // Configurar adaptador con los datos obtenidos de los libros
                    adapter = LibroAdapter(libros)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("MainActivity", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.e("MainActivity", "Error en la petición: ${t.message}")
            }
        })
    }

}