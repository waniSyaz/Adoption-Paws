package com.example.adoptionpets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {
    // Declare all components
    private lateinit var profileName: TextView
    private lateinit var profileEmail: TextView
    private lateinit var profilePhone: TextView
    private lateinit var profilePassword: TextView
    private lateinit var edit : ImageButton
    private lateinit var btnBack : ImageButton

    private lateinit var mAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        //Initialize component
        edit = findViewById(R.id.pencil)
        profileName = findViewById(R.id.profileName)
        profileEmail = findViewById(R.id.profileEmail)
        profilePhone = findViewById(R.id.profilePhone)
        profilePassword = findViewById(R.id.profilePassword)
        btnBack = findViewById<ImageButton>(R.id.btn_back)
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

        btnBack.setOnClickListener{
            val i = Intent(this@ProfileActivity, MainActivity4::class.java)
            startActivity(i)
        }
        edit.setOnClickListener{
            val i = Intent(this@ProfileActivity, UserEditActivity::class.java)
            startActivity(i)
        }

        // Retrieve user data from Firebase
        val userId = mAuth.currentUser?.uid
        userId?.let { uid ->
            dbRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val name = dataSnapshot.child("userName").getValue(String::class.java)
                        val email = dataSnapshot.child("userEmail").getValue(String::class.java)
                        val phone = dataSnapshot.child("userPhone").getValue(String::class.java)
                        val password = dataSnapshot.child("userPassword").getValue(String::class.java)

                        // Display user data in the profile page UI
                        profileName.text = name
                        profileEmail.text = email
                        profilePhone.text = phone
                        profilePassword.text = password

                    } else {
                        Toast.makeText(this@ProfileActivity, "User data not found", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("AccountClass", "User data not found")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                    Toast.makeText(
                        this@ProfileActivity,
                        "Failed to retrieve user data: ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("UserAccount", "Failed to retrieve user data: ${databaseError.message}")
                }
            })
        }
    }

    // Function to navigate to the specified activity
    private fun navigateToActivity(activityClass: Class<*>, extraData: Bundle? = null) {
        val intent = Intent(this@ProfileActivity, activityClass)
        extraData?.let { intent.putExtras(it) }
        startActivity(intent)
    }
}
