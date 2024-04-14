package com.example.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class EditPetActivity : AppCompatActivity() {

    private lateinit var spinnerPetsNo: Spinner
    private lateinit var no : EditText
    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var cat : RadioButton
    private lateinit var dog : RadioButton
    private lateinit var breed : EditText
    private lateinit var update : Button
    private lateinit var databaseReference: DatabaseReference
    private var selectedPetType: String = "" // Variable to store the selected pet type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_pet)

        no = findViewById(R.id.eTNo)
        name = findViewById(R.id.eTName)
        age = findViewById(R.id.eTAge)
        cat = findViewById<RadioButton>(R.id.buttonCat)
        dog = findViewById<RadioButton>(R.id.buttonDog)
        breed = findViewById(R.id.eTBreed)
        update = findViewById(R.id.Update)

        // Set click listeners for the pet type buttons
        cat.setOnClickListener {
            selectedPetType = "Cat"
            dog.isChecked = false // Uncheck the dog button
        }

        dog.setOnClickListener {
            selectedPetType = "Dog"
            cat.isChecked = false // Uncheck the cat button
        }

        databaseReference = FirebaseDatabase.getInstance().reference.child("Pets")

        val petsNo = intent.getStringExtra("petsNo") ?: ""
        no.setText(petsNo)

        // Fetch existing data from Firebase
        databaseReference.child(petsNo).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val pets = dataSnapshot.getValue(Pets::class.java)
                    pets?.let {
                        name.setText(it.petsName)
                        age.setText(it.petsAge)
                        breed.setText(it.petsBreed)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Toast.makeText(this@EditPetActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })

        update.setOnClickListener {
            val petsNo = no.text.toString().trim()
            val petsName = name.text.toString().trim()
            val petsAge = age.text.toString().trim()
            val petsBreed = breed.text.toString().trim()

            if (petsNo.isEmpty() || petsName.isEmpty() || petsAge.isEmpty() || selectedPetType.isEmpty() || petsBreed.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()

            }

            saveData(petsNo, petsName, petsAge, selectedPetType, petsBreed)
        }
    }

    private fun saveData(petsNo: String, n: String, a: String, t: String, b: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Pets")

        val pets = Pets(petsNo, n, a, t, b)

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
