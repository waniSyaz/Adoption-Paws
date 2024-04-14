package com.example.adoptionpets

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class UserManualActivity : AppCompatActivity() {
    //Declare component
    private lateinit var book : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_manual)
        //Initialize component
        book = findViewById(R.id.tutoBook)
        val originalTextColor = Color.WHITE // Change to your original text color
        val clickedColor = Color.BLUE

        // Set click listeners for other buttons
        book.setOnClickListener {
            // Change text color to indicate it has been clicked
            book.setTextColor(clickedColor)

            // Use Handler to reset text color after a delay
            Handler().postDelayed({
                book.setTextColor(originalTextColor)
            }, 1000)
            //connect to next page
            val i = Intent(this, TutoBookActivity::class.java)
            startActivity(i)
        }
    }
}