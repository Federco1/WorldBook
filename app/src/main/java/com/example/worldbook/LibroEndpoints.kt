package com.example.worldbook

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LibroEndpoints {
    @GET("volumes")
    fun searchBooksByAuthor(
        @Query("q") author: String
    ): Call<BookResponse>
}