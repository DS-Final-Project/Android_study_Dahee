package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        //val countUpButton: Button = findViewById(R.id.count_up_button)
        //val resetButton: Button = findViewById(R.id.reset_button)

        rollButton.setOnClickListener { rollDice() }
        //countUpButton.setOnClickListener{ countUp() }
        //resetButton.setOnClickListener { reset() }
    }

    private fun rollDice() {
        val randomInt = (1..6).random()
        val diceImage: ImageView = findViewById(R.id.dice_img)
        val drawableResource =when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        Toast.makeText(this, "Roll Dice", Toast.LENGTH_SHORT).show()

    }
/*
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

 */
}