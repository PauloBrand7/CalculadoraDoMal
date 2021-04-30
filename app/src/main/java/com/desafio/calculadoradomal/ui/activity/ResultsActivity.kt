package com.desafio.calculadoradomal.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.database.AppDatabase
import com.desafio.calculadoradomal.ui.recyclerview.ResultsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultsActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        rvList = findViewById(R.id.results_list)
    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            val results = db.resultDAO().getAll()
            withContext(Dispatchers.Main){
                rvList.adapter = ResultsAdapter(results)
            }
        }
    }

}