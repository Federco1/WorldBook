package com.example.worldbook

//data class Book()
data class BookResponse(
    val items: List<BookItem>
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks? // Agregamos el enlace a la imagen
)

data class ImageLinks(
    val thumbnail: String // Contiene la URL de la imagen de la portada
)

data class Libro(
    var id: Int,
    var titulo:String,
    var autor:String,
    var fecha:String
)
