package com.example.baseconventer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var baseRadioGroup: RadioGroup
    private lateinit var binaryOutput: TextView
    private lateinit var octalOutput: TextView
    private lateinit var decimalOutput: TextView
    private lateinit var hexadecimalOutput: TextView
    private lateinit var inputField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initviews()
        setListeners()
    }

    private fun setListeners() {

        baseRadioGroup.setOnCheckedChangeListener { _, _ ->
            updateOutputs(inputField.text.toString())
        }

        inputField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateOutputs(s.toString())
            }
        })
    }

    private fun initviews() {

        baseRadioGroup = findViewById(R.id.baseRadioGroup)
        binaryOutput = findViewById(R.id.binaryOutput)
        octalOutput = findViewById(R.id.octalOutput)
        decimalOutput = findViewById(R.id.decimalOutput)
        hexadecimalOutput = findViewById(R.id.hexadecimalOutput)
        inputField = findViewById(R.id.inputField)
    }

    private fun updateOutputs(inputStr: String) {

        val invalidChars = "qwrtyuiopsgjhklzxcvbnm@!$#^%*()-_=+{}[]'\":;/?.<>"

        if (inputStr.any { it.lowercaseChar() in invalidChars }) {

            binaryOutput.text = "Invalid input"
            octalOutput.text = "Invalid input"
            decimalOutput.text = "Invalid input"
            hexadecimalOutput.text = "Invalid input"
            return
        }

        val selectedBase = when (baseRadioGroup.checkedRadioButtonId) {
            R.id.binaryRadio -> 2
            R.id.octalRadio -> 8
            R.id.decimalRadio -> 10
            R.id.hexadecimalRadio -> 16
            else -> 10
        }


        val inputInt = try {
            inputStr.toLong(selectedBase).toInt()
        } catch (e: NumberFormatException) {
            0
        }

        binaryOutput.text = inputInt.toString(2)
        octalOutput.text = inputInt.toString(8)
        decimalOutput.text = inputInt.toString(10)
        hexadecimalOutput.text = inputInt.toString(16)
    }
}