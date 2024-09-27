package com.example.worldbook

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatosUsuario::class], version = 1)
abstract class DataBase: RoomDatabase(){
    abstract fun DatosUsuarioDao(): DatosUsuarioDao

    companion object{
        private var instancia: DataBase? = null
        fun getDatabase(context: Context):DataBase{
            if (instancia == null){
                synchronized(this){
                    instancia = Room.databaseBuilder(context,
                        DataBase::class.java, "DatosUsuario_database"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigrationFrom()
                        .build()
                }
            }
            return instancia!!
        }
    }
}