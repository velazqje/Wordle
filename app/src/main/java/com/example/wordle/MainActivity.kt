package com.example.wordle

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val secretWord = FourLetterWordList.getRandomFourLetterWord()

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

        val HiddenSecretWord = findViewById<TextView>(R.id.secretWord)

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

            val userInputGuess = findViewById<EditText>(R.id.userInputGuess)
//            val result = checkGuess(userInputGuess)

            hideKeyboard()

//            if (guessCount == 0) {
            //                secretWord.setText(FourLetterWordList.getRandomFourLetterWord())

//            }

            guessCount++


            if (guessCount == 1) {
                firstGuessHeading.visibility = View.VISIBLE
                firstGuess.setText(userInputGuess.text.toString().uppercase())
                firstGuess.visibility = View.VISIBLE
                checkGuess(firstGuess.toString())
                firstGuessCheckHeading.visibility = View.VISIBLE
                firstGuessCheck.visibility = View.VISIBLE
                userInputGuess.setText("")

            }


            else if (guessCount == 2) {
                secondGuessHeading.visibility = View.VISIBLE
                secondGuess.setText(userInputGuess.text.toString().uppercase())
                secondGuess.visibility = View.VISIBLE
                checkGuess(secondGuess.toString())
                secondGuessCheckHeading.visibility = View.VISIBLE
                secondGuessCheck.visibility = View.VISIBLE
                userInputGuess.setText("")
            }


            else if (guessCount == 3) {
                thirdGuessHeading.visibility = View.VISIBLE
                thirdGuess.setText(userInputGuess.text.toString().uppercase())
                thirdGuess.visibility = View.VISIBLE
                checkGuess(thirdGuess.toString())
                thirdGuessCheckHeading.visibility = View.VISIBLE
                thirdGuessCheck.visibility = View.VISIBLE
                guessBtn.visibility = View.INVISIBLE
                resetBtn.visibility = View.VISIBLE
                HiddenSecretWord.setText(secretWord)
                HiddenSecretWord.visibility = View.VISIBLE
                userInputGuess.setText("")


                // if stopbutton is visible , reset counter to 0
                if (resetBtn.visibility == View.VISIBLE) {
                    guessCount = 0
                }

                Toast.makeText(it.context, "Number of Guesses Exceeded", Toast.LENGTH_SHORT).show()

            }



        }


    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
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
            if (guess[i] == secretWord[i]) {
                result += "O"
            }
            else if (guess[i] in secretWord) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}