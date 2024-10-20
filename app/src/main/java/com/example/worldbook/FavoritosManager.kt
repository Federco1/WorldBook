package com.example.worldbook

object FavoritosManager {
    private val listaFavoritos = mutableListOf<BookItem>()

    fun agregarFavorito(libro: BookItem) {
        listaFavoritos.add(libro)
    }

    fun getFavoritos(): List<BookItem> {
        return listaFavoritos
    }
}
