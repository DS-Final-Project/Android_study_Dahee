package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        val countUpButton: Button = findViewById(R.id.count_up_button)
        val resetButton: Button = findViewById(R.id.reset_button)

        rollButton.setOnClickListener { rollDice() }
        countUpButton.setOnClickListener{ countUp() }
        resetButton.setOnClickListener { reset() }
    }

    private fun rollDice() {
        val resultText: TextView = findViewById(R.id.result_text)
        val randomInt = (1..6).random()
        Toast.makeText(this, "Roll Dice", Toast.LENGTH_SHORT).show()
        resultText.text = randomInt.toString()
    }

    private fun countUp() {
        val resultText: TextView = findViewById(R.id.result_text)
        Toast.makeText(this, "Count Up", Toast.LENGTH_SHORT).show()
        if(resultText.text == "Hello World!") {
            resultText.text = "1"
        }else{
            var resultInt = resultText.text.toString().toInt()
            if(resultInt <6) {
                resultInt++
                resultText.text = resultInt.toString()
            }
        }
    }

    private fun reset() {
        val resultText: TextView = findViewById(R.id.result_text)
        Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show()
        resultText.text = "0"
    }
}