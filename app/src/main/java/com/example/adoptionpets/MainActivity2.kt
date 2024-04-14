package com.example.adoptionpets

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var login: Button
    private lateinit var signup: Button
    private lateinit var user : TextView

    //declare the firebase
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Initialize component
        email = findViewById(R.id.eTEmail)
        password = findViewById(R.id.eTPassword)
        login = findViewById(R.id.btnLogin)
        signup = findViewById(R.id.btnSignup)
        user = findViewById(R.id.manual)

        val originalTextColor = Color.BLACK // Change to your original text color
        val clickedColor = Color.BLUE

        // Set click listeners for other buttons
        user.setOnClickListener {
            // Change text color to indicate it has been clicked
            user.setTextColor(clickedColor)

            // Use Handler to reset text color after a delay
            Handler().postDelayed({
                user.setTextColor(originalTextColor)
            }, 1000)
            //connect to next page
            val intent = Intent(this, UserManualActivity::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("User")



        signup.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                login(email, password)
            } else {
                Toast.makeText(this@MainActivity2, "All fields are mandatory", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    Toast.makeText(this@MainActivity2, "Login successful", Toast.LENGTH_SHORT)
                        .show()
                    // Navigate to the home page (MainActivity5)
                    val intent = Intent(this@MainActivity2, MainActivity4::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this@MainActivity2, "Authentication failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}