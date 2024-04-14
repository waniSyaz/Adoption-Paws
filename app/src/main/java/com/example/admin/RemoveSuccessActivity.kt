package com.example.admin

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class RemoveSuccessActivity : AppCompatActivity() {
    //declare variable
    private lateinit var home : ImageButton
    private lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_success)

        home = findViewById(R.id.home3)
        back = findViewById(R.id.back3)

        //initialize component
        home.setOnClickListener {
            val intent = Intent(this@RemoveSuccessActivity, Home::class.java)
            startActivity(intent)
        }

        back.setOnClickListener {
            val intent = Intent(this@RemoveSuccessActivity, ProfilesActivity::class.java)
            startActivity(intent)
        }
    }
}