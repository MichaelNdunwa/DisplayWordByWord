package com.michael.displaywordbyword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {

    private lateinit var myTextView: TextView
    private lateinit var show: Button
    private lateinit var delete: Button
    private lateinit var textToDisplay: String
    private var currentIndex = 0
    val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTextView = findViewById(R.id.my_textView)
        show = findViewById(R.id.display_text)
        delete = findViewById(R.id.delete_text)

        textToDisplay = getString(R.string.about_michael)

        show.setOnClickListener {
            // Call a function to start displaying text word by word
            displayTextWordByWord()
        }

        delete.setOnClickListener { deleteTextWordByWord() }
    }

    private fun displayTextWordByWord() {

        // Define a runnable to update text
        val updateTextRunnable = object : Runnable {
            override fun run() {
                if(currentIndex < textToDisplay.length) {
                    val currentChar = textToDisplay[currentIndex]
                    myTextView.append(currentChar.toString())
                    currentIndex++

                    // Call the runnable again after a delay (adjust duration as needed)
                    handler.postDelayed(this, 100)
                }
            }
        }

        // Start the initial runnable
        handler.post(updateTextRunnable)
    }

    private fun deleteTextWordByWord() {
        // Define a runnable to delete text
        val deleteTextRunnable = object : Runnable {
            override fun run() {
                if (currentIndex > 0) {
                    // Remove the last character
                    currentIndex--
                    val newText = textToDisplay.substring(0, currentIndex)
                    myTextView.text = newText

                    // Call the runnable again after a delay (adjust duration as needed)
                    handler.postDelayed(this, 100)
                }
            }
        }

        // Start the initial runnable to delete text
        handler.post(deleteTextRunnable)
    }


}