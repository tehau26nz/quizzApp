package com.tehaunuiss.myquizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tVName: TextView = findViewById<TextView>(R.id.tVName)
        val tVScore: TextView = findViewById<TextView>(R.id.tVScore)
        val btnFinish: Button = findViewById<Button>(R.id.btnFinish)

        tVName.text = intent.getStringExtra(Constants.USER_NAME)

        // Amount of correct answers over the total # of questions
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        tVScore.text = "Your score is $correctAnswers out of $totalQuestions"

        // Send back to the Main Activity
        btnFinish.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}