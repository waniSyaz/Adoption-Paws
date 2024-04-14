package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserEditActivity : AppCompatActivity() {
    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var phone : EditText
    private lateinit var password : EditText
    private lateinit var arrow : ImageButton
    private lateinit var update : Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_edit)

        name = findViewById(R.id.txt_name)
        email = findViewById(R.id.txt_email)
        phone = findViewById(R.id.txt_phone)
        password = findViewById(R.id.txt_password)
        arrow = findViewById(R.id.ank)
        update = findViewById(R.id.update_user)

        arrow.setOnClickListener {
            val intent = Intent(this@UserEditActivity, ProfileActivity::class.java)
            startActivity(intent)
        }

        val userId = intent.getStringExtra("userId") ?: ""
        val userEmail = intent.getStringExtra("userEmail") ?: ""
        name.setText(userId)
        email.setText(userEmail)

        databaseReference = FirebaseDatabase.getInstance().reference.child("User")

        databaseReference.child(userEmail).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val model = dataSnapshot.getValue(Model::class.java)
                    model?.let {
                        name.setText(it.userName)
                        phone.setText(it.userPhone)
                        password.setText(it.userPassword)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@UserEditActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })

        update.setOnClickListener {
            val userName = name.text.toString().trim()
            val userPhone = phone.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userName.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                saveData(userId, userEmail, userName, userPassword, userPhone)
            }
        }
    }

    private fun saveData(userId: String, userEmail: String, userName: String, userPassword: String, userPhone: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        val model = Model(userId, userEmail, userName, userPassword, userPhone)

        databaseReference.child(userEmail).setValue(model)
            .addOnSuccessListener {
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to update data: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
