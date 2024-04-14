package com.example.adoptionpets

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val booking = findViewById<ImageButton>(R.id.btnBooking)
        val aware = findViewById<ImageButton>(R.id.btnAware)
        val calculator = findViewById<ImageButton>(R.id.btnCalc)
        val faqs = findViewById<TextView>(R.id.faqs)
        val user = findViewById<TextView>(R.id.userManual)
        val home = findViewById<ImageButton>(R.id.home4)
        val profiles = findViewById<ImageButton>(R.id.profiles4)
        val learn = findViewById<ImageButton>(R.id.learn4)
        val acc = findViewById<ImageButton>(R.id.acc4)
        val originalTextColor = Color.BLACK // Change to your original text color
        val clickedColor = Color.BLUE

        // Set click listeners for other buttons
        faqs.setOnClickListener {
            // Change text color to indicate it has been clicked
            faqs.setTextColor(clickedColor)

            // Use Handler to reset text color after a delay
            Handler().postDelayed({
                faqs.setTextColor(originalTextColor)
            }, 1000)
            //connect to next page
            navigateToActivity(FaqsActivity::class.java)
        }
        user.setOnClickListener {
            // Change text color to indicate it has been clicked
            user.setTextColor(clickedColor)

            // Use Handler to reset text color after a delay
            Handler().postDelayed({
                user.setTextColor(originalTextColor)
            }, 1000)
            //connect to next page
            navigateToActivity(TutoBookActivity::class.java)
        }

        booking.setOnClickListener {
            navigateToActivity(BookingActivity::class.java)
        }

        aware.setOnClickListener {
            navigateToActivity(AwareActivity::class.java)
        }

        calculator.setOnClickListener {
            navigateToActivity(CalculatorActivity::class.java)
        }
        home.setOnClickListener {
            navigateToActivity(MainActivity4::class.java)
        }
        profiles.setOnClickListener {
            navigateToActivity(PetListActivity::class.java)
        }
        learn.setOnClickListener {
            navigateToActivity(LearningActivity::class.java)
        }
        acc.setOnClickListener {
            navigateToActivity(ProfileActivity::class.java)
        }
    }

    // Function to navigate to the specified activity
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
