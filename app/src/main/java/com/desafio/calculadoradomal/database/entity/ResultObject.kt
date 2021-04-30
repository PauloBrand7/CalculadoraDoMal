package com.desafio.calculadoradomal.database.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result")
data class ResultObject(
        var firstValue : String,
        var secondValue : String,
        var operator : String,
        var result : Double = 0.0
) {
        @PrimaryKey(autoGenerate = true) var id : Long = 0
}