package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.wordleapp.FourLetterWordList.getRandomFourLetterWord
import org.w3c.dom.Text
import java.util.*
import kotlin.random.Random
import kotlin.reflect.typeOf
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var guesses = 0;
        //Get a random word and keep a counter of the number of guess
        //when user press the button, clear the editext field and check the work
        //Then display the text for the guess
        //If user is right show the guess and say they are right using a toast
        //If user hits 3 guess then show the word and show they lsot

        val toast = Toast.makeText(applicationContext, wordToGuess, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        findViewById<Button>(R.id.button).setOnClickListener {
            val userGuess = findViewById<EditText>(R.id.guess);
            val userWord = userGuess.getText().toString()
            val correctness = checkGuess(userWord.uppercase());

            if (guesses == 0) {
                findViewById<TextView>(R.id.guess1).visibility = View.VISIBLE;
                findViewById<TextView>(R.id.check1).visibility = View.VISIBLE;
                var guessWord = findViewById<TextView>(R.id.guessWord1)
                guessWord.text = userWord;
                guessWord.visibility = View.VISIBLE;
                var checkWord = findViewById<TextView>(R.id.checkWord1)
                checkWord.text = correctness;
                checkWord.visibility = View.VISIBLE;
                guesses++;
            } else if (guesses == 1) {
                findViewById<TextView>(R.id.guess2).visibility = View.VISIBLE;
                findViewById<TextView>(R.id.check2).visibility = View.VISIBLE;
                var guessWord = findViewById<TextView>(R.id.guessWord2)
                guessWord.text = userWord;
                guessWord.visibility = View.VISIBLE;
                var checkWord = findViewById<TextView>(R.id.checkWord2)
                checkWord.text = correctness;
                checkWord.visibility = View.VISIBLE;
                guesses++;
            } else if (guesses == 2) {
                findViewById<TextView>(R.id.guess3).visibility = View.VISIBLE;
                findViewById<TextView>(R.id.check3).visibility = View.VISIBLE;
                var guessWord = findViewById<TextView>(R.id.guessWord3)
                guessWord.text = userWord;
                guessWord.visibility = View.VISIBLE;
                var checkWord = findViewById<TextView>(R.id.checkWord3)
                checkWord.text = correctness;
                checkWord.visibility = View.VISIBLE;
                guesses++;
            }

            if (guesses > 2 || correctness == "OOOO") {
                val message = if (correctness == "OOOO") "Congratualtions, you win." else "Sorry, you lose.";
                val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                val answer = findViewById<TextView>(R.id.answer);
                answer.text = wordToGuess.uppercase();
                answer.visibility = View.VISIBLE;
                findViewById<EditText>(R.id.guess).visibility = View.INVISIBLE;
                findViewById<Button>(R.id.button).visibility = View.INVISIBLE;
            }
//            var guess = findViewById<TextView>(R.id.guess1);
//            println(guess)
//            var GuessNumber = findViewById<TextView>(R.id.guess1);
//            GuessNumber.visibility = View.VISIBLE;
//
//            findViewById<TextView>(R.id.check1).visibility = View.VISIBLE;
//            findViewById<TextView>(R.id.answer).visibility = View.VISIBLE;
            userGuess.text.clear();
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
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}