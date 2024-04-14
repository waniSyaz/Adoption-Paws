package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    //declare component
    private lateinit var login: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize component
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        login = findViewById(R.id.btnLogin)

        database = FirebaseDatabase.getInstance().reference.child("User")

        login.setOnClickListener {
            val emailStr = email.text.toString()
            val passwordStr = password.text.toString()

            if (emailStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                login(emailStr, passwordStr)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "All fields are mandatory",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        database.orderByChild("userEmail").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (userSnapshot in dataSnapshot.children) {
                            val model = userSnapshot.getValue(Model::class.java)

                            if (model != null && model.userPassword == password) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login Successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(this@MainActivity, Home::class.java)
                                intent.putExtra(
                                    "userId",
                                    userSnapshot.key
                                ) // Pass user ID to profile activity
                                startActivity(intent)
                                finish()
                                return
                            }
                        }
                    }
                    Toast.makeText(this@MainActivity, "Login Failed", Toast.LENGTH_LONG).show()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        this@MainActivity,
                        "Database Error: ${databaseError.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}