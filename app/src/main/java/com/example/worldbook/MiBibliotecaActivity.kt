package com.example.worldbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

class MiBibliotecaActivity : AppCompatActivity() {
    lateinit var rvLibros: RecyclerView
    lateinit var librosAdapter: LibroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mi_biblioteca)
       // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         //   val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
           // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            //insets
        //}

        rvLibros = findViewById(R.id.rvLibros)
        librosAdapter = LibroAdapter(getLibros(),this)
        rvLibros.adapter = librosAdapter

        //val libros = arrayOf(
            //"Cumbres borrascosas",
            //"Frankenstein",
            //"Jane Eyre",
            //"Mujercitas",
            //"Orgullo y prejuicio"
        //)

        //val listView: ListView = findViewById(R.id.listaLibros)
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, libros)
        //listView.adapter = adapter

    }

    private fun getLibros(): MutableList<Libro> {
        var libros: MutableList<Libro> = ArrayList()
        libros.add(Libro(1,"El Hobbit","J R R Tolkien","1937-09-21"))
        libros.add(Libro(2,"Mujercitas","Louisa May Alcott","1868-11-29"))
        libros.add(Libro(3,"Orgullo y prejuicio","Jane Austen","1813-01-28"))
        libros.add(Libro(4,"El Resplandor","Stephen King","1977-01-28"))
        libros.add(Libro(5,"1984","George Orwell","1949-06-08"))
        libros.add(Libro(6,"Frankenstein","Mary Shelley","1818-01-01"))
        libros.add(Libro(7,"Rayuela","Julio Cortazar","1963-06-28"))
        libros.add(Libro(8,"El Codigo Da Vinci","Dan Brown","2003-03-18"))
        libros.add(Libro(9,"El Alquimista","Paulo Coelho","1988-01-01"))
        libros.add(Libro(10,"El Exorcista","W P Blatty","1949-06-08"))
        libros.add(Libro(11,"Harry Potter y la piedra filosofal","J k Rowling","1997-06-26"))
        libros.add(Libro(12,"Alicia en el pais de las maravillas","Lewis Carrol","1865-11-26"))
        libros.add(Libro(13,"Guerra y Paz","Liev N Tolstoi","1865-01-01"))

        return libros

    }
}