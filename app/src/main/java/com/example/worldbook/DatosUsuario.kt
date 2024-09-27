package com.example.worldbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "datos_usuario")
data class DatosUsuario(
    @ColumnInfo(name="email") var email: String,
    @ColumnInfo(name="usuario")var usuario: String,
    @ColumnInfo(name="password")var password : String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

