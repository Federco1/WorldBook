package com.example.worldbook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LibroAdapter (var libros: List<BookItem>) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>(){

    class LibroViewHolder (view: View): RecyclerView.ViewHolder(view){

        val title: TextView = view.findViewById(R.id.bookTitle)
        val author: TextView = view.findViewById(R.id.bookAuthor)
        val coverImage: ImageView = view.findViewById(R.id.bookCoverImage) // Agregamos el ImageView

        /*lateinit var txtTitulo: TextView
        lateinit var txtAutor: TextView
        lateinit var txtFecha: TextView

        init {
            txtTitulo= view.findViewById(R.id.tv_titulo)
            txtAutor = view.findViewById(R.id.tv_autor)
            txtFecha = view.findViewById(R.id.tv_fecha)
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(view)
    }

    //override fun getItemCount()= libros.size
    override fun getItemCount(): Int {
        return libros.size
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {

        val book = libros[position]
        holder.title.text = book.volumeInfo.title
        holder.author.text = book.volumeInfo.authors.joinToString(", ")



        // Usamos Glide para cargar la imagen
        val imageUrl = book.volumeInfo.imageLinks?.thumbnail
        if (imageUrl != null) {
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(holder.coverImage)
        } else {
            holder.coverImage.setImageResource(android.R.drawable.ic_menu_report_image) // Imagen por defecto si no hay portada

        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, describirLibro::class.java)

            // Pasar los datos del libro al Intent
            intent.putExtra("title", book.volumeInfo.title)
            intent.putExtra("author", book.volumeInfo.authors.joinToString(", "))
            intent.putExtra("coverUrl", book.volumeInfo.imageLinks?.thumbnail)
            intent.putExtra("publisher", book.volumeInfo.publisher)
            intent.putExtra("publishedDate", book.volumeInfo.publishedDate)
            intent.putExtra("description", book.volumeInfo.description)

            holder.itemView.context.startActivity(intent)


            /*val item= libros.get(position)
        holder.txtTitulo.text= item.titulo
        holder.txtAutor.text= item.autor
        holder.txtFecha.text= item.fecha*/
        }
        )
    }

}