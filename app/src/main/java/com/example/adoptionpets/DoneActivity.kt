package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class DoneActivity : AppCompatActivity() {
    //declare component
    private lateinit var home : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.done)
        //initialize component
        home = findViewById<ImageButton>(R.id.btnHouse)

        home.setOnClickListener{
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
        }
    }
}