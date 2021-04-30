package com.desafio.calculadoradomal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desafio.calculadoradomal.database.dao.ResultDAO
import com.desafio.calculadoradomal.database.entity.ResultObject

@Database(entities = [ResultObject ::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDAO() : ResultDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "resultados.db")
            .build()
    }

}