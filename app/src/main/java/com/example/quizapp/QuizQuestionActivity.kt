package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), OnClickListener {
    private var questionList: ArrayList<Question>? = null
    private var questionNumber: Int = 1
    private var mSelectedOption: Int = 0
    private var correctAnswers: Int = 0
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null
    private var userName: String? = null
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

        userName = intent.getStringExtra(Constants.USER_NAME)
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
        defaultOptionView()
        tvQuestion?.text = questionList!![questionNumber - 1].question
        tvImage?.setImageResource(questionList!![questionNumber - 1].image)
        tvProgressBar?.progress = questionNumber
        tvProgressNumber?.text = "${questionNumber}/${questionList!!.size}"
        tvOptionOne?.text = questionList!![questionNumber - 1].optionOne
        tvOptionTwo?.text = questionList!![questionNumber - 1].optionTwo
        tvOptionThree?.text = questionList!![questionNumber - 1].optionThree
        tvOptionFour?.text = questionList!![questionNumber - 1].optionFour

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)

        tvSubmitBtn?.setOnClickListener(this)

        if (questionNumber == questionList!!.size) {
            tvSubmitBtn?.text = "FINISH"
        } else {
            tvSubmitBtn?.text = "SUBMIT"
        }
    }

    private fun defaultOptionView() {
        var options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }
        for (option in options) {
            option.setTextColor(Color.BLACK)
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_options_bg
            )
        }

    }

    private fun selectedOptionView(selectedOption: TextView, selectedOptionNumber: Int) {
        defaultOptionView()
        mSelectedOption = selectedOptionNumber
        selectedOption.setTextColor(Color.BLUE)
        selectedOption.setTypeface(Typeface.DEFAULT_BOLD, selectedOptionNumber)
        selectedOption.background = ContextCompat.getDrawable(this, R.drawable.selected_option_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOne ->
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }

            R.id.optionTwo ->
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            R.id.optionThree ->
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            R.id.optionFour ->
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            R.id.tv_submit_button -> {
                if (mSelectedOption == 0) {
                    questionNumber++

                    when {
                        questionNumber <= questionList!!.size -> {
                            setQuestion()
                        }

                        else -> {
                            Toast.makeText(this, "You made it to the end", Toast.LENGTH_SHORT)
                                .show()
                            var intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.TOTAL_QUESTION, questionList!!.size)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            startActivity(intent)
                            finish()
                        }

                    }
                } else {
                    when {
                        (mSelectedOption != questionList!![questionNumber - 1].correctAnswer) -> {
                            answserResult(mSelectedOption, R.drawable.wrong_options_bg)
                        }

                        else -> {
                            correctAnswers++
                        }
                    }
                    answserResult(
                        questionList!![questionNumber - 1].correctAnswer,
                        R.drawable.correct_options_bg
                    )
                    if (questionNumber < questionList!!.size) {
                        tvSubmitBtn?.text = "Go to next Question"
                    }else{
                        tvSubmitBtn?.text = "See Results"

                    }
                    mSelectedOption = 0
                }
            }
        }
    }

    private fun answserResult(answer: Int, drawable: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.let {
                    it.background = ContextCompat.getDrawable(this, drawable)
                }
            }

            2 -> {
                tvOptionTwo?.let {
                    it.background = ContextCompat.getDrawable(this, drawable)
                }
            }

            3 -> {
                tvOptionThree?.let {
                    it.background = ContextCompat.getDrawable(this, drawable)
                }
            }

            4 -> {
                tvOptionFour?.let {
                    it.background = ContextCompat.getDrawable(this, drawable)
                }
            }
        }
    }
}