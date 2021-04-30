package com.desafio.calculadoradomal.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.database.AppDatabase
import com.desafio.calculadoradomal.model.Calculation
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var input : TextView
    private lateinit var result : TextView
    private lateinit var firstHistory : TextView
    private lateinit var secondHistory : TextView
    private lateinit var thirdHistory : TextView
    private lateinit var contextButton : ImageButton
    private var calculation = Calculation()
    private var check = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        contextButton = findViewById(R.id.context_menu)
        registerForContextMenu(contextButton)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.resultados -> {
                val intent = Intent(this,ResultsActivity::class.java)
                startActivity(intent)
            }
            R.id.contato -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, "paulo.brand77@gmail.com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contato")
                intent.putExtra(Intent.EXTRA_TEXT, "Olá, Gostaria de entrar em contato!!")
                startActivity(Intent.createChooser(intent, "Enviar Email"))
            }
            R.id.sobre -> {
                startActivity(Intent(this,AboutActivity::class.java))
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()

        input = findViewById(R.id.math_operation)
        result = findViewById(R.id.result)
        firstHistory = findViewById(R.id.first_history)
        secondHistory = findViewById(R.id.second_history)
        thirdHistory = findViewById(R.id.third_history)

        clickButtons()
    }

    override fun onClick(v: View?) {
        val db = AppDatabase(this)
        when(v?.id){
            //numbers
            R.id.zero -> updateInput("0")
            R.id.one -> updateInput("1")
            R.id.two ->updateInput("2")
            R.id.three ->updateInput("3")
            R.id.four ->updateInput("4")
            R.id.five ->updateInput("5")
            R.id.six ->updateInput("6")
            R.id.seven ->updateInput("7")
            R.id.eight ->updateInput("8")
            R.id.nine ->updateInput("9")

            //operations
            R.id.plus ->{
                secondOperation()
                updateInput("+")
                check = true
            }
            R.id.minus ->{
                secondOperation()
                updateInput("-")
                check = true
            }
            R.id.multiply ->{
                secondOperation()
                updateInput("*")
                check = true
            }
            R.id.divide ->{
                secondOperation()
                updateInput("/")
                check = true
            }
            R.id.percent ->{
                secondOperation()
                updateInput("%")
                check = true
            }

            //others
            R.id.point ->updateInput(".")
            R.id.clear ->{
                input.text = "0"
                check = false
            }
            R.id.equal ->{
                mathHistory()
                result.text = formatResult(calculation.returnResult(input.text).result)

                CoroutineScope(Dispatchers.IO).launch {
                    db.resultDAO().insert(calculation.returnResult(input.text))
                }
                check = true
            }
            R.id.context_menu -> contextButton.showContextMenu()
        }
    }

    private fun clickButtons() {
        //numbers
        zero.setOnClickListener(this)
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)

        //operations
        plus.setOnClickListener(this)
        minus.setOnClickListener(this)
        multiply.setOnClickListener(this)
        divide.setOnClickListener(this)
        percent.setOnClickListener(this)

        //others
        point.setOnClickListener(this)
        clear.setOnClickListener(this)
        equal.setOnClickListener(this)
        contextButton.setOnClickListener(this)
    }

    private fun updateInput(num : String) {
        if (input.text != "0") {
            input.text = StringBuilder().append(input.text).append(num)
        } else {
            input.text = num
        }
    }

    private fun mathHistory(){
        thirdHistory.text = secondHistory.text
        secondHistory.text = firstHistory.text
        firstHistory.text = result.text.drop(1)
    }

    private fun formatResult(result: Double) : String{
        val decimalFormat = DecimalFormat("0.#")
        return StringBuilder().append("=").append(decimalFormat.format(result)).toString()
    }

    private fun secondOperation(){
        if(check){
                input.text = formatResult(calculation.returnResult(input.text).result).drop(1)
        }
    }

}