package com.desafio.calculadoradomal.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.desafio.calculadoradomal.model.ResultObject

@Dao
interface ResultDAO {

    @Insert
    fun insert(resultado : ResultObject)

    @Query(value = "DELETE FROM result WHERE id = :id")
    fun delete(id : Int)

    @Query(value = "DELETE FROM result")
    fun deleteAll()

    @Query(value = "SELECT * FROM result ORDER BY id DESC")
    fun getAll() : List<ResultObject>

}