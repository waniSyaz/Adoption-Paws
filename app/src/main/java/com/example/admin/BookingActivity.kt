package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class BookingActivity : AppCompatActivity() {
    //declare component
    private lateinit var update : ImageButton
    private lateinit var remove : ImageButton
    private lateinit var arrow : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        //initialize component
        update = findViewById(R.id.Update2)
        remove = findViewById(R.id.Remove2)
        arrow = findViewById(R.id.arrow2)

        update.setOnClickListener {
            val intent = Intent(this@BookingActivity, BookinglistActivity::class.java)
            startActivity(intent)
        }

        remove.setOnClickListener {
            val intent = Intent(this@BookingActivity, RemoveBookingActivity::class.java)
            startActivity(intent)
        }

        arrow.setOnClickListener {
            val intent = Intent(this@BookingActivity, Home::class.java)
            startActivity(intent)
        }
    }
}