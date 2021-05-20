package com.desafio.calculadoradomal.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.desafio.calculadoradomal.database.AppDatabase
import com.desafio.calculadoradomal.model.ResultObject
import com.desafio.calculadoradomal.ui.activity.CalculatorActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {
    val db = AppDatabase(CalculatorActivity())
    val resultsLiveData: LiveData<List<ResultObject>>
        get() = _resultsLiveData
    private val _resultsLiveData = MutableLiveData<List<ResultObject>>()

    fun fetchResults() {
        CoroutineScope(Dispatchers.IO).launch {
            _resultsLiveData.postValue(db.resultDAO().getAll())
        }
    }

}