package com.example.worldbook

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LibroEndpoints {
    @GET("volumes")
    fun searchBooks(
        @Query("q") query: String
    ): Call<BookResponse>
}