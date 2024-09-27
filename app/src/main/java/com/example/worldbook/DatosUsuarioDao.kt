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
}