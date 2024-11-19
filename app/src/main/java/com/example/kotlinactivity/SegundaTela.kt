package com.example.kotlinactivity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinactivity.databinding.ActivitySegundaTelaBinding


class SegundaTela : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaTelaBinding

    private val kmPerLiter = 12.0
    private val gasPrice = 5.85
    private val tollPrice = 10.50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda_tela)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivitySegundaTelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val distanceStr = binding.editDistance.text.toString().trim()
            val kmStr = binding.editKm.text.toString().trim()
            val tollsStr = binding.editTolls.text.toString().trim()

            if (distanceStr.isNotEmpty() && kmStr.isNotEmpty() && tollsStr.isNotEmpty()) {
                val distance = distanceStr.toDoubleOrNull()
                val km = kmStr.toDoubleOrNull()
                val tolls = tollsStr.toDoubleOrNull()

                if (distance != null && km != null && tolls != null) {

                    val timeSpent = distance / km
                    val literUsed = distance / kmPerLiter
                    val fuelCost = literUsed * gasPrice
                    val tollCost = tolls * tollPrice
                    val totalCost = fuelCost + tollCost

                    val result = """
                        Tempo estimado de viagem: %.2f horas
                        Quantidade de litros utilizados: %.2f litros
                        Custo total com combustível: R$ %.2f
                        Custo total com pedágios: R$ %.2f
                        Custo total da viagem: R$ %.2f
                    """.trimIndent().format(timeSpent, literUsed, fuelCost, tollCost, totalCost)

                    binding.textView.text = result

                } else {
                    binding.textView.text = getString(R.string.invalid_values_error)
                }
            } else {
                binding.textView.text = getString(R.string.fill_all_fields_error)
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