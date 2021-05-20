package com.desafio.calculadoradomal.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "result")
class ResultObject(
        var firstValue: String,
        var secondValue: String,
        var operator: String,
        var result: Double = 0.0
) {
    var time: String = ""
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun returnResult(input: CharSequence) {
        time = SimpleDateFormat(
                "dd/MM/yyyy hh:mm",
                Locale.forLanguageTag("pt_BR")
        ).format(Calendar.getInstance().time)
        var temp = ""

        input.forEach {
            if (!it.isDigit()) {
                if (it.toString() == ".") {
                    temp += it
                } else {
                    operator = it.toString()
                    firstValue += temp
                    temp = ""
                }
            } else {
                temp += it
            }
        }
        secondValue = temp

        if (secondValue == "") secondValue = "0"

        when (operator) {
                "+" -> result = firstValue.toDouble() + secondValue.toDouble()
                "-" -> result = firstValue.toDouble() - secondValue.toDouble()
                "*" -> result = firstValue.toDouble() * secondValue.toDouble()
                "/" -> result = firstValue.toDouble() / secondValue.toDouble()
                "%" -> {
                        secondValue = ""
                        result = firstValue.toDouble() / 100
                }
        }

    }

    fun resetResult() {
        firstValue = ""
        secondValue = ""
        operator = ""
        result = 0.0
    }

}