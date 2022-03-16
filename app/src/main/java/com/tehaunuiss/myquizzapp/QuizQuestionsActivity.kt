package com.tehaunuiss.myquizzapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    private var progressBar: ProgressBar? = null
    private var tVProgress: TextView? = null
    private var tVQuestion: TextView? = null
    private var iVImage: ImageView? = null

    private var tVOptionOne: TextView? = null
    private var tVOptionTwo: TextView? = null
    private var tVOptionThree: TextView? = null
    private var tVOptionFour: TextView? = null
    private var btnSubmit : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)

        progressBar = findViewById(R.id.progressBar)
        tVProgress = findViewById(R.id.tVProgress)
        tVQuestion = findViewById(R.id.tVQuestion)
        iVImage = findViewById(R.id.iVImage)

        tVOptionOne = findViewById(R.id.tVOptionOne)
        tVOptionTwo = findViewById(R.id.tVOptionTwo)
        tVOptionThree = findViewById(R.id.tVOptionThree)
        tVOptionFour = findViewById(R.id.tVOptionFour)
        btnSubmit = findViewById(R.id.btnSubmit)
        tVOptionOne?.setOnClickListener(this)
        tVOptionTwo?.setOnClickListener(this)
        tVOptionThree?.setOnClickListener(this)
        tVOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        // Loads the questions
        mQuestionList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        // To get the question
        val question: Question = mQuestionList!![mCurrentPosition - 1]

        // Setup everything
        iVImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        tVProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tVQuestion?.text = question.question
        tVOptionOne?.text = question.optionOne
        tVOptionTwo?.text = question.optionTwo
        tVOptionThree?.text = question.optionThree
        tVOptionFour?.text = question.optionFour

        // Display submit or finish
        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit?.text = "DONE"
        }else{
            btnSubmit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tVOptionOne?.let{
            options.add(0, it)
        }
        tVOptionTwo?.let{
            options.add(1, it)
        }
        tVOptionThree?.let{
            options.add(2, it)
        }
        tVOptionFour?.let{
            options.add(3, it)
        }

        // Change TextView button in UI according to answers
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }


    }

    private fun selectedOptionView(tV: TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tV.setTextColor(Color.parseColor("#363A43"))
        tV.setTypeface(tV.typeface, Typeface.BOLD)
        tV.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tVOptionOne ->{
                tVOptionOne?.let{
                    selectedOptionView(it, 1)
                }
            }
            R.id.tVOptionTwo ->{
                tVOptionTwo?.let{
                    selectedOptionView(it, 2)
                }
            }
            R.id.tVOptionThree ->{
                tVOptionThree?.let{
                    selectedOptionView(it, 3)
                }
            }
            R.id.tVOptionFour ->{
                tVOptionFour?.let{
                    selectedOptionView(it, 4)
                }
            }

            R.id.btnSubmit ->{
                // TODO "implement btn submit"
            }
        }
    }
}