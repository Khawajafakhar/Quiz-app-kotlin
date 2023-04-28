package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var tvUserName = findViewById<TextView>(R.id.tv_username)
        var tvUserScore = findViewById<TextView>(R.id.tv_user_score)
        var tvSubmitBtnFinish = findViewById<Button>(R.id.tv_finish_button)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        tvUserName.text = userName

        val totalQuestions =intent.getIntExtra(Constants.TOTAL_QUESTION,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        tvUserScore.text = "You Scored $correctAnswers out of $totalQuestions"

        tvSubmitBtnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}