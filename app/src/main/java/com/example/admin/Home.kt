package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private lateinit var pets: ImageButton
    private lateinit var booking: ImageButton
    private lateinit var user: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        //initialize component
        pets = findViewById(R.id.btnProfiles)
        booking = findViewById(R.id.btnBooking)

        pets.setOnClickListener {
            //connect to the next page
            val intent = Intent(this@Home, ProfilesActivity::class.java)
            startActivity(intent)
        }

        booking.setOnClickListener {
            //connect to the booking page
            val intent = Intent(this@Home, BookingActivity::class.java)
            startActivity(intent)
        }

        user.setOnClickListener {
            //connect to the user page
            val intent = Intent(this@Home, UserActivity::class.java)
            startActivity(intent)
        }
    }
}