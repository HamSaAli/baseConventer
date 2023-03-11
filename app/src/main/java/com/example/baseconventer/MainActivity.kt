package com.example.baseconventer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binaryOutput by lazy { findViewById<TextView>(R.id.binaryOutput) }
        val octalOutput by lazy { findViewById<TextView>(R.id.octalOutput) }
        val decimalOutput by lazy { findViewById<TextView>(R.id.decimalOutput) }
        val hexadecimalOutput by lazy { findViewById<TextView>(R.id.hexadecimalOutput) }
        val inputField by lazy { findViewById<EditText>(R.id.inputField) }


        class BaseTextWatcher : TextWatcher {

            private var prevInputStr = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val inputStr = s.toString().lowercase()
                if (inputStr != prevInputStr) {
                    val filteredStr = inputStr.filter {
                        it !in setOf(
                            'g',
                            'h',
                            'i',
                            'j',
                            'k',
                            'l',
                            'm',
                            'n',
                            'o',
                            'p',
                            'q',
                            'r',
                            's',
                            't',
                            'u',
                            'v',
                            'w',
                            'x',
                            'y',
                            'z'
                        )
                    }
                    prevInputStr = filteredStr
                    (s as Editable).replace(0, s.length, filteredStr)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val inputStr = s.toString()

                if (inputStr.isNotEmpty()) {
                    var inputInt = 0

                    for (c in inputStr) {
                        if (c.isDigit()) {
                            inputInt = inputInt * 10 + c.toString().toInt()
                        } else {
                            val charToIntMap = mapOf(
                                'a' to 10, 'b' to 11, 'c' to 12,
                                'd' to 13, 'e' to 14, 'f' to 15
                            )

                            val charValue = charToIntMap[c.lowercaseChar()]
                            if (charValue != null) {
                                inputInt = inputInt * 16 + charValue
                            }
                        }
                    }

                    binaryOutput.text = inputInt.toString(2)
                    octalOutput.text = inputInt.toString(8)
                    decimalOutput.text = inputInt.toString(10)
                    hexadecimalOutput.text = inputInt.toString(16)

                } else {
                    binaryOutput.text = ""
                    octalOutput.text = ""
                    decimalOutput.text = ""
                    hexadecimalOutput.text = ""
                }
            }
        }
        inputField.addTextChangedListener(BaseTextWatcher())
    }

}