package com.example.baseconventer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField =  findViewById<EditText>(R.id.inputField)
        val baseRadioGroup =  findViewById<RadioGroup>(R.id.baseRadioGroup)


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

    private fun updateOutputs(inputStr: String) {
        val baseRadioGroup = findViewById<RadioGroup>(R.id.baseRadioGroup)
        val binaryOutput = findViewById<TextView>(R.id.binaryOutput)
        val octalOutput = findViewById<TextView>(R.id.octalOutput)
        val decimalOutput = findViewById<TextView>(R.id.decimalOutput)
        val hexadecimalOutput = findViewById<TextView>(R.id.hexadecimalOutput)


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