package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage = findViewById(R.id.dice_img)

        val rollButton: Button = findViewById(R.id.roll_button)
        //val countUpButton: Button = findViewById(R.id.count_up_button)
        val clearButton: Button = findViewById(R.id.clear_button)

        rollButton.setOnClickListener { rollDice() }
        //countUpButton.setOnClickListener{ countUp() }
        clearButton.setOnClickListener { clear() }
    }

    private fun rollDice() {
        val drawableResource =when ((1..6).random()) {
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


 */
    private fun clear() {
        Toast.makeText(this, "Clear", Toast.LENGTH_SHORT).show()
        diceImage.setImageResource(R.drawable.empty_dice)
    }

}