package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionActivity : AppCompatActivity(),OnClickListener {
    private var questionList: ArrayList<Question>? = null
    private var questionNumber : Int = 1
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null
    private var tvProgressBar: ProgressBar? = null
    private var tvProgressNumber: TextView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var tvSubmitBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        tvQuestion = findViewById(R.id.tv_question)
        tvImage = findViewById(R.id.tv_image)
        tvProgressBar = findViewById(R.id.tv_progress)
        tvProgressNumber = findViewById(R.id.tv_progress_numbers)
        tvOptionOne = findViewById(R.id.optionOne)
        tvOptionTwo = findViewById(R.id.optionTwo)
        tvOptionThree = findViewById(R.id.optionThree)
        tvOptionFour = findViewById(R.id.optionFour)
        tvSubmitBtn = findViewById(R.id.tv_submit_button)
       questionList = Constants.getQuestion()

        setQuestion()


    }

    private fun setQuestion() {

        tvQuestion?.text = questionList!![questionNumber - 1].question
        tvImage?.setImageResource(questionList!![questionNumber - 1].image)
        tvProgressBar?.progress = questionNumber
        tvProgressNumber?.text = "${questionNumber}/${questionList!!.size}"
        tvOptionOne?.text = questionList!![questionNumber - 1].optionOne
        tvOptionTwo?.text = questionList!![questionNumber - 1].optionTwo
        tvOptionThree?.text = questionList!![questionNumber - 1].optionThree
        tvOptionFour?.text = questionList!![questionNumber - 1].optionFour
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}