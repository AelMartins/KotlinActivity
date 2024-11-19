package com.example.kotlinactivity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinactivity.databinding.ActivityQuartaTelaBinding

class QuartaTela : AppCompatActivity() {
    private lateinit var binding: ActivityQuartaTelaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quarta_tela)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityQuartaTelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCelsiusToFahrenheit.setOnClickListener {
            val celsius = binding.editTextTemperature.text.toString()

            if (celsius.isNotEmpty()) {
                val celsiusValue = celsius.toDoubleOrNull()
                if (celsiusValue != null) {
                    val fahrenheit = (celsiusValue * 9/5) + 32
                    binding.textViewResult.text = getString(R.string.result_label, fahrenheit)
                } else {
                    Toast.makeText(this, R.string.invalid_temperature_error, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, R.string.empty_temperature_error, Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonFahrenheitToCelsius.setOnClickListener {
            val fahrenheit = binding.editTextTemperature.text.toString()

            if (fahrenheit.isNotEmpty()) {
                val fahrenheitValue = fahrenheit.toDoubleOrNull()
                if (fahrenheitValue != null) {
                    val celsius = (fahrenheitValue - 32) * 5/9
                    binding.textViewResult.text = getString(R.string.result_label, celsius)
                } else {
                    Toast.makeText(this, R.string.invalid_temperature_error, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, R.string.empty_temperature_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}