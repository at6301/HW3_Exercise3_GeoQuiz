package com.thies.hw3_exercise3_geoquiz

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.thies.hw3_exercise3_geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Adding list
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // Variable to keep track of index as working through strings
    private var currentIndex = 0
    // counter to keep track of the number of right answers
    private final var rightAnswersCounter : Int = 0
    // integer variable to hold score
    private var score : Int = 0
    // result string
    private lateinit var resultString : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener{
            checkAnswer(true)
            disableButtons()
        }
        binding.falseButton.setOnClickListener{
            checkAnswer(false)
            disableButtons()
        }
        binding.nextButton.setOnClickListener{
            // check if it is last question
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            enableButtons()
        }
        updateQuestion()
    }
    private fun updateQuestion() {
        if(currentIndex == questionBank.size) {
            calculateScore()
        }
        else{
            val questionTextResId = questionBank[currentIndex].textResId
            binding.questionTextview.setText(questionTextResId)
        }
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        // increment number of right answers
        if(userAnswer == correctAnswer) {
            rightAnswersCounter++
        }

        val messageResId =
            if(userAnswer == correctAnswer) {
            R.string.correct_toast
            }
            else {
            R.string.incorrect_toast
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun calculateScore() {
        score = rightAnswersCounter / questionBank.size
        showResults()
    }

    private fun showResults() {
        resultString = "You scored a: "
        Toast.makeText(this, resultString, Toast.LENGTH_LONG).show()
    }

    private fun disableButtons() {
        binding.falseButton.isEnabled = false
        binding.trueButton.isEnabled = false
    }

    private fun enableButtons() {
        binding.falseButton.isEnabled = true
        binding.trueButton.isEnabled = true
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}