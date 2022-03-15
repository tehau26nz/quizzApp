package com.tehaunuiss.myquizzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)

        // Load the questions
        val questionsList = Constants.getQuestions()
        Log.i("QuestionsList size is", "${questionsList.size}")

    }
}