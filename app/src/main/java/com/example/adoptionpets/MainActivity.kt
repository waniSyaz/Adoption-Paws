package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //Declare component
    private lateinit var start : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize component
        start = findViewById(R.id.btnStart)

        start.setOnClickListener {
            //declare variable i to connect next page/activity
            //val - static variable
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }
    }
}