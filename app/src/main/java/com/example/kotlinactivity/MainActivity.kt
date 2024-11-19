package com.example.kotlinactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val travelCalculatorButton = findViewById<Button>(R.id.travel_calculator)
        val moneyConverterButton = findViewById<Button>(R.id.money_converter)
        val temperatureMeterButton = findViewById<Button>(R.id.temperature_meter)

        travelCalculatorButton.setOnClickListener {
            val intent = Intent(this, SegundaTela::class.java)
            startActivity(intent)
        }

        moneyConverterButton.setOnClickListener {
            val intent = Intent(this, TerceiraTela::class.java)
            startActivity(intent)
        }

        temperatureMeterButton.setOnClickListener {
            val intent = Intent(this, QuartaTela::class.java)
            startActivity(intent)
        }
    }
}