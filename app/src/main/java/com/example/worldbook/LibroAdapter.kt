package com.example.worldbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LibroAdapter (var libros: MutableList<Libro>, var context: Context):
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>(){

    class LibroViewHolder (view: View): RecyclerView.ViewHolder(view){
        lateinit var txtTitulo: TextView
        lateinit var txtAutor: TextView
        lateinit var txtFecha: TextView

        init {
            txtTitulo= view.findViewById(R.id.tv_titulo)
            txtAutor = view.findViewById(R.id.tv_autor)
            txtFecha = view.findViewById(R.id.tv_fecha)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(view)
    }

    override fun getItemCount()= libros.size

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val item= libros.get(position)
        holder.txtTitulo.text= item.titulo
        holder.txtAutor.text= item.autor
        holder.txtFecha.text= item.fecha
    }

}