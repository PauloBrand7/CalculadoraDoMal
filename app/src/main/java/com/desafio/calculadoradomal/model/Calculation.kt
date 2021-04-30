package com.desafio.calculadoradomal.model

import com.desafio.calculadoradomal.database.entity.ResultObject

class Calculation() {

    fun returnResult(input: CharSequence): ResultObject {

        var auxiliar = ResultObject("","","",0.0)
        var aux = ""

        input.forEach {
            if (!it.isDigit()) {
                if (it.toString() == ".") {
                    aux += it
                } else {
                    auxiliar.operator = it.toString()
                    auxiliar.firstValue += aux
                    aux = ""
                }
            } else {
                aux += it
            }
        }
        auxiliar.secondValue = aux

            when (auxiliar.operator) {
                "+" -> auxiliar.result = auxiliar.firstValue.toDouble() + auxiliar.secondValue.toDouble()
                "-" -> auxiliar.result = auxiliar.firstValue.toDouble() - auxiliar.secondValue.toDouble()
                "*" -> auxiliar.result = auxiliar.firstValue.toDouble() * auxiliar.secondValue.toDouble()
                "/" -> auxiliar.result = auxiliar.firstValue.toDouble() / auxiliar.secondValue.toDouble()
                "%" -> auxiliar.result = auxiliar.firstValue.toDouble() / 100
                else -> auxiliar.result = auxiliar.secondValue.toDouble()
            }

        return auxiliar
    }

}

