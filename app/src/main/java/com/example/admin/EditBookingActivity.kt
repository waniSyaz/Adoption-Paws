package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditBookingActivity : AppCompatActivity() {
    //declare component
    private lateinit var no : EditText
    private lateinit var name : EditText
    private lateinit var cat : RadioButton
    private lateinit var dog : RadioButton
    private lateinit var fName : EditText
    private lateinit var update : Button
    private lateinit var arrow : ImageButton
    private lateinit var databaseReference: DatabaseReference
    private var selectedPetType: String = "" // Variable to store the selected pet type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_booking)
        //initialize component
        no = findViewById(R.id.eTbNo)
        name = findViewById(R.id.eTbName)
        cat = findViewById(R.id.cat2)
        dog = findViewById(R.id.dog2)
        fName = findViewById(R.id.eTbFullName)
        update = findViewById(R.id.Update3)
        arrow = findViewById(R.id.arrow4)

        arrow.setOnClickListener {
            //connect to the next page
            val intent = Intent(this@EditBookingActivity, BookingActivity::class.java)
            startActivity(intent)
        }

        // Set click listeners for the pet type buttons
        cat.setOnClickListener {
            selectedPetType = "Cat"
            dog.isChecked = false // Uncheck the dog button
        }

        dog.setOnClickListener {
            selectedPetType = "Dog"
            cat.isChecked = false // Uncheck the cat button
        }

        databaseReference = FirebaseDatabase.getInstance().reference.child("Booking")

        val fullName = intent.getStringExtra("fullName") ?: ""
        no.setText(fullName)

        // Fetch existing data from Firebase
        databaseReference.child(fullName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val pets = dataSnapshot.getValue(Pets::class.java)
                    pets?.let {
                        no.setText(it.petsNo)
                        name.setText(it.petsName)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Toast.makeText(this@EditBookingActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })

        update.setOnClickListener {
            val petsNo = no.text.toString().trim()
            val petsName = name.text.toString().trim()
            val fullName = fName.text.toString().trim()

            if (petsNo.isEmpty() || petsName.isEmpty() || fullName.isEmpty() || selectedPetType.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()

            }

            saveData(petsNo, petsName, fullName, selectedPetType)
        }
    }

    private fun saveData(petsNo: String, n: String, f: String, t: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Booking")

        val pets = Pets(petsNo, n, f, t)

        databaseReference.child(petsNo).setValue(pets)
            .addOnSuccessListener {
                val i = Intent(this, EditSuccessActivity::class.java)
                startActivity(i)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to update data: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}