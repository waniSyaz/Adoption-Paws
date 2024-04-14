package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DoglearnActivity : AppCompatActivity() {
    //declare component
    private lateinit var previous : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doglearn)
        //initialize component
        previous = findViewById(R.id.btnPrev)

        previous.setOnClickListener {
            val i = Intent(this, LearningActivity::class.java)
            startActivity(i)
        }
    }
}