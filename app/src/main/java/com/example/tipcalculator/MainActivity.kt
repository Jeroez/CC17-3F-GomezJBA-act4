package com.example.tipcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val priceEditText: TextInputEditText = findViewById(R.id.price)
        val resultTextView: TextView = findViewById(R.id.Total)

        button.setOnClickListener {
            val priceInput = priceEditText.text.toString()
            if (priceInput.isEmpty()) {
                Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price: Double = try {
                priceInput.toDouble()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selection = radioGroup.checkedRadioButtonId
            val tip: Double = when (selection) {
                R.id.radioButton1 -> price * 0.20
                R.id.radioButton2 -> price * 0.18
                R.id.radioButton3 -> price * 0.15
                else -> {
                    Toast.makeText(this, "Please select a tip percentage", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }



            resultTextView.text = String.format("%.2f", tip)        }
    }
}
