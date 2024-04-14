package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class AwareActivity : AppCompatActivity() {
    //declare component
    private lateinit var arrow : ImageButton
    private lateinit var nav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aware)

        //initialize component
        arrow = findViewById(R.id.btn_arrow)
        val home = findViewById<ImageButton>(R.id.home4)
        val profiles = findViewById<ImageButton>(R.id.profiles4)
        val learn = findViewById<ImageButton>(R.id.learn4)
        val acc = findViewById<ImageButton>(R.id.acc4)

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

        arrow.setOnClickListener {
            //declare variable i to connect next page/activity
            //val - static variable
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
        }
    }
    // Function to navigate to the specified activity
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}