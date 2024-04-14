package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {
    //Declare database
    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    //Declare all component
    private lateinit var username: EditText
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var phone: EditText
    private lateinit var register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //Initialize all component
        username = findViewById(R.id.eTemail)
        name = findViewById(R.id.eTName)
        password = findViewById(R.id.eTPass)
        phone = findViewById(R.id.eTPhone)
        register = findViewById(R.id.btnRegister)

        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("User")

        // Set OnClickListener for signup
        register.setOnClickListener {
            val userEmail = username.text.toString()
            val userName = name.text.toString()
            val userPassword = password.text.toString()
            val userPhone = phone.text.toString()

            if (userPassword.length < 6) {
                Toast.makeText(
                    this,
                    "Your password must be at least 6 characters",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Check if any field is empty
            if (userEmail.isEmpty() || userName.isEmpty() || userPassword.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = mAuth.currentUser?.uid ?: ""
                        val user = Model(userId, userEmail, userName, userPassword, userPhone)
                        dbRef.child(userId).setValue(user)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    this,
                                    "User registration successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // Optional: You can call saveData function here if you want to execute it separately.
                                // saveData(email, name, password, phone)
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    } else {
                        Toast.makeText(
                            this,
                            "Failed to register user: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}