package edu.cinec.smartguru

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView
    private lateinit var btnViewResult: Button
    private lateinit var cardResult: CardView
    private lateinit var tvScore: TextView
    private lateinit var tvResultMessage: TextView

    private var selectedOptionIndex = -1 // 1 for A, 2 for B, 3 for C, 4 for D

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        findViewById<ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }

        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        btnViewResult = findViewById(R.id.btnViewResult)
        cardResult = findViewById(R.id.cardResult)
        tvScore = findViewById(R.id.tvScore)
        tvResultMessage = findViewById(R.id.tvResultMessage)

        option1.setOnClickListener { selectOption(1) }
        option2.setOnClickListener { selectOption(2) }
        option3.setOnClickListener { selectOption(3) }
        option4.setOnClickListener { selectOption(4) }

        btnViewResult.setOnClickListener {
            if (selectedOptionIndex == -1) {
                Toast.makeText(this, "Please select an answer first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            cardResult.visibility = View.VISIBLE

            if (selectedOptionIndex == 1) {
                // Correct Answer
                tvScore.text = "100 / 100"
                tvScore.setTextColor(Color.parseColor("#12B76A"))
                tvResultMessage.text = "Congratulations! Excellent! Option A is correct."
                tvResultMessage.setTextColor(Color.parseColor("#12B76A"))
            } else {
                // Incorrect Answer
                tvScore.text = "0 / 100"
                tvScore.setTextColor(Color.parseColor("#D92D20"))
                tvResultMessage.text = "Incorrect. Correct answer is A) Interactive MCQ Practice."
                tvResultMessage.setTextColor(Color.parseColor("#D92D20"))
            }

            Toast.makeText(this, "Result Calculated!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectOption(optionIndex: Int) {
        selectedOptionIndex = optionIndex

        val selectedBg = ContextCompat.getDrawable(this, R.drawable.bg_role_selected)
        val unselectedBg = ContextCompat.getDrawable(this, R.drawable.bg_role_unselected)

        option1.background = if (optionIndex == 1) selectedBg else unselectedBg
        option1.setTextColor(if (optionIndex == 1) Color.WHITE else Color.parseColor("#344054"))

        option2.background = if (optionIndex == 2) selectedBg else unselectedBg
        option2.setTextColor(if (optionIndex == 2) Color.WHITE else Color.parseColor("#344054"))

        option3.background = if (optionIndex == 3) selectedBg else unselectedBg
        option3.setTextColor(if (optionIndex == 3) Color.WHITE else Color.parseColor("#344054"))

        option4.background = if (optionIndex == 4) selectedBg else unselectedBg
        option4.setTextColor(if (optionIndex == 4) Color.WHITE else Color.parseColor("#344054"))
    }
}
