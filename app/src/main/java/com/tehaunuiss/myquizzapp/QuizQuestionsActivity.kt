package com.tehaunuiss.myquizzapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.TintableCompoundDrawablesView
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

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

        // To receive string from Main Activity
        mUserName = intent.getStringExtra(Constants.USER_NAME)

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
        // Reset all the options to be grey
        defaultOptionsView()
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
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            /*Toast.makeText(this,"You've made it to the end", Toast.LENGTH_LONG).show()*/

                            // To send the app to the next activity i.e Result Activity
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    // If answer is incorrect do this
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else{
                        // Increment correct answers by 1
                        mCorrectAnswers++
                    }
                    // If correct do that
                     answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit?.text = "DONE"
                    }else{
                        btnSubmit?.text = "NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        // Check if the answer is correct
        when(answer){
            1 ->{
                tVOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 ->{
                tVOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 ->{
                tVOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 ->{
                tVOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}