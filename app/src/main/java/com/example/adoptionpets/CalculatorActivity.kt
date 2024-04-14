package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {
    //declare component
    private lateinit var food : EditText
    private lateinit var litter : EditText
    private lateinit var grooming : EditText
    private lateinit var care : EditText
    private lateinit var other : EditText
    private lateinit var calc : Button
    private lateinit var reset : Button
    private lateinit var arrow : ImageButton
    private lateinit var status : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)
        //initialize all component
        food = findViewById(R.id.eTFood)
        litter = findViewById(R.id.eTLitter)
        grooming = findViewById(R.id.eTGrooming)
        care = findViewById(R.id.eTCare)
        other = findViewById(R.id.eTOther)
        calc = findViewById<Button>(R.id.btnCalculate)
        reset = findViewById(R.id.btnReset)
        arrow = findViewById<ImageButton>(R.id.btnBack)
        status = findViewById(R.id.tVStatus)
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

        calc.setOnClickListener {

            //declare the variable
            val food:Double = food.text.toString().toDouble()
            val litter:Double = litter.text.toString().toDouble()
            val grooming:Double = grooming.text.toString().toDouble()
            val care:Double = care.text.toString().toDouble()
            val other:Double = other.text.toString().toDouble()

            //calculate total
            val total = food + litter + grooming + care + other

            //initialize function decimal
            val df = DecimalFormat("#,###,###.##")

            //display the result at TextView
            status.text = "  " + df.format(total).toString()

        }

        reset.setOnClickListener{
            //to reset all records
            food.setText("")
            litter.setText("")
            grooming.setText("")
            care.setText("")
            other.setText("")
            status.setText("")
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