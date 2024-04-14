package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class LearningActivity : AppCompatActivity() {
    //declare component
    private lateinit var arrow : ImageButton
    private lateinit var dog : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learning)
        //initialize component
        arrow = findViewById<ImageButton>(R.id.buttonBack)
        dog = findViewById<Button>(R.id.btnDogie)

        arrow.setOnClickListener{
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
        }
        dog.setOnClickListener {
            val i = Intent(this, DoglearnActivity::class.java)
            startActivity(i)
        }

    }
}