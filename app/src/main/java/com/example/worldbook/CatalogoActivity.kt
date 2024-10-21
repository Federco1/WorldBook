package com.example.worldbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchField: EditText
    private lateinit var searchButtonByAuthor: Button
    private lateinit var adapter: LibroAdapter
    private lateinit var toolbar: Toolbar
    private var libros: List<BookItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Opciones"

        recyclerView = findViewById(R.id.bookList)
        searchField = findViewById(R.id.searchField)
        searchButtonByAuthor = findViewById(R.id.searchButtonByAuthor)

        // Configurar el RecyclerView con un LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LibroAdapter(libros)
        recyclerView.adapter = adapter

        // Establecer acción del botón de búsqueda
        searchButtonByAuthor.setOnClickListener {
            val query = searchField.text.toString().trim()
            if (query.isNotEmpty()) {
                searchBooksByAuthor(query) // Hacer la búsqueda
            } else {
                Toast.makeText(this, "Ingresar autor para buscar", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchBooksByAuthor(author: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(LibroEndpoints::class.java)
                    .searchBooksByAuthor("inauthor:$author")
                    .execute()

                withContext(Dispatchers.Main) {
                    if (call.isSuccessful) {
                        val booksResponse = call.body()
                        if (booksResponse != null) {
                            // Filtrar libros por coincidencia exacta del autor
                            val filteredBooks = booksResponse.items.filter { book ->
                                book.volumeInfo.authors?.any {
                                    it.equals(author, ignoreCase = true)
                                } == true
                            }
                            if (filteredBooks.isNotEmpty()) {
                                initBooks(filteredBooks)
                            } else {
                                showErrorDialog("No se encontraron libros del autor: $author")
                            }
                        } else {
                            showErrorDialog("No se encontraron libros.")
                        }
                    } else {
                        showErrorDialog("Error en la respuesta: ${call.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showErrorDialog("Se produjo un error: ${e.localizedMessage}")
                }
            }
        }
    }

    private fun initBooks(filteredBooks: List<BookItem>) {
        libros = filteredBooks
        adapter = LibroAdapter(libros)
        recyclerView.adapter = adapter
    }

    private fun showErrorDialog(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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