package com.example.kotlinactivity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlinactivity.databinding.ActivityTerceiraTelaBinding

class TerceiraTela : AppCompatActivity() {
    private lateinit var binding: ActivityTerceiraTelaBinding

    private val conversionRate = 0.20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terceira_tela)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityTerceiraTelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConverter.setOnClickListener {
            val reaisString = binding.editReal.text.toString()

            if (reaisString.isNotEmpty()) {
                val reais = reaisString.toDoubleOrNull()

                if (reais != null) {
                    val dolares = reais * conversionRate
                    // Usando recurso de string com placeholders para formatar o valor
                    binding.textViewResult.text = getString(R.string.result_in_dollars, dolares)
                } else {
                    Toast.makeText(this, R.string.invalid_value_error, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, R.string.fill_value_error, Toast.LENGTH_SHORT).show()
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