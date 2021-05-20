package com.desafio.calculadoradomal.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.database.AppDatabase
import com.desafio.calculadoradomal.model.ResultObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class ResultsAdapter(
    private val results: List<ResultObject>
) : Adapter<ResultsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = results.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val result: TextView = view.findViewById(R.id.resultado)
        private val date: TextView = view.findViewById(R.id.date)

        fun bind(item: ResultObject) {
            val decimalFormat = DecimalFormat("0.#")
            result.text = StringBuilder().append(item.firstValue).append(item.operator)
                .append(item.secondValue).append(" = ").append(decimalFormat.format(item.result))
                .toString()
            date.text = item.time

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "APAGANDO ITEM", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    AppDatabase(itemView.context).resultDAO().deleteAll()
                }
            }
        }
    }

}