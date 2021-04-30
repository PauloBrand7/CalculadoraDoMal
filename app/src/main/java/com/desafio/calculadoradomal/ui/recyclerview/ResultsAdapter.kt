package com.desafio.calculadoradomal.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.database.entity.ResultObject

class ResultsAdapter(
        private val resultados : List<ResultObject>
) : Adapter<ResultsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = resultados.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.result_item,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultados[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val firstValue : TextView = view.findViewById(R.id.first_value_result)
        private val operation : TextView = view.findViewById(R.id.operator_result)
        private val secondValue : TextView = view.findViewById(R.id.second_value_result)
        private val result : TextView = view.findViewById(R.id.resultado)

        fun bind(item : ResultObject){
            firstValue.text = item.firstValue
            operation.text = item.operator
            secondValue.text = StringBuilder().append(item.secondValue).append(" = ")
            result.text = item.result.toString()
        }
    }


}