package com.desafio.calculadoradomal.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.desafio.calculadoradomal.R
import com.desafio.calculadoradomal.database.AppDatabase
import com.desafio.calculadoradomal.model.ResultObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var input: TextView
    private lateinit var result: TextView
    private lateinit var firstHistory: TextView
    private lateinit var secondHistory: TextView
    private lateinit var thirdHistory: TextView
    private lateinit var contextButton: ImageButton
    private var resultObject = ResultObject("", "", "", 0.0)
    private var secOp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        contextButton = findViewById(R.id.context_menu)
        registerForContextMenu(contextButton)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.resultados -> startActivity(Intent(this, ResultsActivity::class.java))
            R.id.contato -> {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, "paulo.brand77@gmail.com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Contato")
                intent.putExtra(Intent.EXTRA_TEXT, "Olá, Gostaria de entrar em contato!!")
                startActivity(Intent.createChooser(intent, "Enviar Email"))
            }
            R.id.sobre -> startActivity(Intent(this, AboutActivity::class.java))
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
    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.context_menu) {
            contextButton.showContextMenu()
        } else {
            val button: Button = v as Button
            updateInput(button.text.toString())
        }

    }

    private fun updateInput(num: String) {
        val db = AppDatabase(this)

        when (num) {
            "%","/", "*", "+", "-" -> {
                if (secOp) {
                    resultObject.resetResult()
                    resultObject.returnResult(input.text)
                    input.text = StringBuilder().append(formatResult(resultObject.result).drop(1))
                        .append(num)
                } else {
                    input.text = StringBuilder().append(input.text).append(num)
                    secOp = true
                }

            }
            "C" -> {
                input.text = "0"
                resultObject.resetResult()
                secOp = false
            }
            "=" -> {
                resultObject.resetResult()
                mathHistory()
                resultObject.returnResult(input.text)
                result.text = formatResult(resultObject.result)

                CoroutineScope(Dispatchers.IO).launch {
                    db.resultDAO().insert(resultObject)
                }
                secOp = true
            }
            else -> {
                if (input.text != "0") {
                    input.text = StringBuilder().append(input.text).append(num)
                } else {
                    input.text = num
                }

            }
        }
    }

    private fun mathHistory() {
        thirdHistory.text = secondHistory.text
        secondHistory.text = firstHistory.text
        firstHistory.text = result.text.drop(1)
    }

    private fun formatResult(result: Double): String {
        val decimalFormat = DecimalFormat("0.#")
        return StringBuilder().append("=").append(decimalFormat.format(result)).toString()
    }

}