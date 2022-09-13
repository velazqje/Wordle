package com.example.wordle

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

     private val secretWord = FourLetterWordList.getRandomFourLetterWord()
//    private val secretWord = "GIRL"

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var guessCount = 0

        val hiddenSecretWord = findViewById<TextView>(R.id.secretWord)

        val firstGuessHeading = findViewById<TextView>(R.id.firstGuessHeading)
        val firstGuessCheckHeading = findViewById<TextView>(R.id.firstGuessCheckHeading)
        val firstGuess = findViewById<TextView>(R.id.firstGuess)
        val firstGuessCheck = findViewById<TextView>(R.id.firstGuessCheck)

        val secondGuessHeading = findViewById<TextView>(R.id.secondGuessHeading)
        val secondGuessCheckHeading = findViewById<TextView>(R.id.secondGuessCheckHeading)
        val secondGuess = findViewById<TextView>(R.id.secondGuess)
        val secondGuessCheck = findViewById<TextView>(R.id.secondGuessCheck)

        val thirdGuessHeading = findViewById<TextView>(R.id.thirdGuessHeading)
        val thirdGuessCheckHeading = findViewById<TextView>(R.id.thirdGuessCheckHeading)
        val thirdGuess = findViewById<TextView>(R.id.thirdGuess)
        val thirdGuessCheck = findViewById<TextView>(R.id.thirdGuessCheck)

        val guessBtn = findViewById<Button>(R.id.guessBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)


        guessBtn.setOnClickListener {
//             Toast.makeText(it.context, "Clicked Button", Toast.LENGTH_SHORT).show()

            val userInputGuess = findViewById<EditText>(R.id.userInputGuess).text.toString()
            val result = checkGuess(userInputGuess)

            hideKeyboard()

            guessCount++


            if (guessCount == 1) {
                firstGuessHeading.visibility = View.VISIBLE
                firstGuess.setText(userInputGuess)
                firstGuess.visibility = View.VISIBLE
                checkGuess(firstGuess.toString())
                firstGuessCheckHeading.visibility = View.VISIBLE
                firstGuessCheck.text = result
                firstGuessCheck.visibility = View.VISIBLE

            }


            else if (guessCount == 2) {
                secondGuessHeading.visibility = View.VISIBLE
                secondGuess.setText(userInputGuess)
                secondGuess.visibility = View.VISIBLE
                checkGuess(secondGuess.toString())
                secondGuessCheckHeading.visibility = View.VISIBLE
                secondGuessCheck.text = result
                secondGuessCheck.visibility = View.VISIBLE
            }


            else if (guessCount == 3) {
                thirdGuessHeading.visibility = View.VISIBLE
                thirdGuess.setText(userInputGuess)
                thirdGuess.visibility = View.VISIBLE
                checkGuess(thirdGuess.toString())
                thirdGuessCheckHeading.visibility = View.VISIBLE
                thirdGuessCheck.text = result
                thirdGuessCheck.visibility = View.VISIBLE
                guessBtn.visibility = View.INVISIBLE
                resetBtn.visibility = View.VISIBLE
                hiddenSecretWord.setText(secretWord)
                hiddenSecretWord.visibility = View.VISIBLE


            }

            if (result == "OOOO") {
                Toast.makeText(it.context, "You win!", Toast.LENGTH_SHORT).show()
                guessBtn.visibility = View.INVISIBLE
                resetBtn.visibility = View.VISIBLE
                hiddenSecretWord.setText(secretWord)
                hiddenSecretWord.visibility = View.VISIBLE
            }

            else if (guessCount == 3){
                if (result != "OOOO")
                {    Toast.makeText(it.context, "Number of Guesses Exceeded", Toast.LENGTH_SHORT).show()
                }
            }


                // if stopbutton is visible , reset counter to 0
//            if (resetBtn.visibility == View.VISIBLE) {
//                guessCount = 0
//                Toast.makeText(it.context, "Resetting the counter for new game", Toast.LENGTH_SHORT).show()

//                firstGuess.visibility = View.INVISIBLE
//                firstGuessCheck.visibility = View.INVISIBLE
//                firstGuessHeading.visibility = View.INVISIBLE
//                firstGuessCheckHeading.visibility = View.INVISIBLE
//                secondGuess.visibility = View.INVISIBLE
//                secondGuessCheck.visibility = View.INVISIBLE
//                secondGuessHeading.visibility = View.INVISIBLE
//                secondGuessCheckHeading.visibility = View.INVISIBLE
//                thirdGuess.visibility = View.INVISIBLE
//                thirdGuessCheck.visibility = View.INVISIBLE
//                thirdGuessHeading.visibility = View.INVISIBLE
//                thirdGuessCheckHeading.visibility = View.INVISIBLE
//                hiddenSecretWord.visibility = View.INVISIBLE
//                guessBtn.visibility = View.VISIBLE
//                resetBtn.visibility = View.INVISIBLE

//            }
        }
    }

    /**
     * Parameters / Fields:
     *   secretWord : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */


    private fun checkGuess(guess: String) : String {

        var result = ""
        for (i in 0..3) {
            if (guess.uppercase()[i] == secretWord.uppercase()[i]) {
                result += "O"
            }
            else if (guess.uppercase()[i] in secretWord.uppercase()) {
                result += "+"
            }
            else {
                result += "X"
            }
        }



        return result
    }

}