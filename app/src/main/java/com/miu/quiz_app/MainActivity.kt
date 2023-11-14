package com.miu.quiz_app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.miu.quiz_app.databinding.ActivityMainBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.submitBtn.setOnClickListener{
            showResultDialog()
        }

        binding.resetBtn.setOnClickListener {
            clearSelections()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showResultDialog() {
        val score = calculateScore(binding.radioGroup1)

        val currentDateAndTime = LocalDateTime.now()

        val resultMessage = "Congratulations! You submitted on $currentDateAndTime, you achieved $score%."

        AlertDialog.Builder(this)
            .setTitle("Quiz Result")
            .setMessage(resultMessage)
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

    private fun calculateScore(radioGroup: RadioGroup): Int {
        var score = 0

        // Check the selected radio button for the first question
        val radioId = radioGroup.checkedRadioButtonId
        val radioButton = findViewById<RadioButton>(radioId)
        if (radioButton != null && radioButton.text == "Fully interoperable, allowing calling Kotlin code from Java and vice versa.") {
            score += 50
        }

        // Check the selected checkboxes for the second question
        val checkbox1 = findViewById<CheckBox>(R.id.option2_1)
        val checkbox2 = findViewById<CheckBox>(R.id.option2_2)
        val checkbox3 = findViewById<CheckBox>(R.id.option2_3)
        val checkbox4 = findViewById<CheckBox>(R.id.option2_4)

        if (checkbox1.isChecked && checkbox2.isChecked && !checkbox3.isChecked && !checkbox4.isChecked) {
            score += 50
        }

        return score
    }

    private fun clearSelections() {
        val radioGroup1 = findViewById<RadioGroup>(R.id.radioGroup1)
        val checkbox1 = findViewById<CheckBox>(R.id.option2_1)
        val checkbox2 = findViewById<CheckBox>(R.id.option2_2)
        val checkbox3 = findViewById<CheckBox>(R.id.option2_3)
        val checkbox4 = findViewById<CheckBox>(R.id.option2_4)

        // Clear radio button selection
        radioGroup1.clearCheck()

        // Clear checkbox selection
        checkbox1.isChecked = false
        checkbox2.isChecked = false
        checkbox3.isChecked = false
        checkbox4.isChecked = false
    }
}