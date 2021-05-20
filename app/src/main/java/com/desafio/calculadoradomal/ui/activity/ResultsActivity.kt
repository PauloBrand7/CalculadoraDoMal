package com.desafio.calculadoradomal.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.ui.recyclerview.ResultsAdapter
import com.desafio.calculadoradomal.ui.viewmodel.ResultsViewModel

class ResultsActivity : AppCompatActivity() {

    private lateinit var rvList: RecyclerView
    private lateinit var viewModel: ResultsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        viewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        rvList = findViewById(R.id.results_list)
    }

    override fun onResume() {
        super.onResume()
        liveDataConfigure()
        viewModel.fetchResults()

    }

    private fun liveDataConfigure() {
        viewModel.resultsLiveData.observe(this, {
            rvList.adapter = ResultsAdapter(it)
        })
    }

}