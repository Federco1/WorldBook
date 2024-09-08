package com.example.worldbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class MiBibliotecaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_biblioteca)

        val libros = arrayOf(
            "Cumbres borrascosas",
            "Frankenstein",
            "Jane Eyre",
            "Mujercitas",
            "Orgullo y prejuicio"
        )

        val listView: ListView = findViewById(R.id.listaLibros)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, libros)
        listView.adapter = adapter
    }
}