package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SuccessActivity : AppCompatActivity() {

    private lateinit var home : ImageButton
    private lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success)

        home = findViewById(R.id.button_home)
        back = findViewById(R.id.button_back)

        home.setOnClickListener{
            val intent = Intent(this@SuccessActivity, Home::class.java)
            startActivity(intent)
        }

        back.setOnClickListener{
            val intent = Intent(this@SuccessActivity, ProfilesActivity ::class.java)
        }
    }
}