package com.example.worldbook

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DatosUsuarioDao {

    @Query("select * from datos_entity")
    fun getAll(): List <DatosUsuario>

    @Insert
    fun insert(DatosUsuario: DatosUsuario)

    @Query("SELECT * FROM datos_entity WHERE usuario = :usuario LIMIT 1")
    fun getUserByUsername(usuario: String): DatosUsuario?

    @Query("SELECT * FROM datos_entity WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): DatosUsuario?
}