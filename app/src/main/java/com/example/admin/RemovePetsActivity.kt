package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RemovePetsActivity : AppCompatActivity() {

    private lateinit var spinnerPetsNo: Spinner
    private lateinit var removeButton: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_pets)

        spinnerPetsNo = findViewById(R.id.spinnerPetsNo)
        removeButton = findViewById(R.id.button)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Pets")

        // Fetch pet numbers from Firebase and populate the Spinner
        fetchPetNumbers()

        removeButton.setOnClickListener {
            // Get the selected pet number from the Spinner
            val selectedPetsNo = spinnerPetsNo.selectedItem.toString()

            // Remove the selected pet from Firebase
            removePet(selectedPetsNo)


        }
    }

    private fun fetchPetNumbers() {
        val petNumbersList = mutableListOf<String>()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val petsNo = snapshot.key
                    petsNo?.let { petNumbersList.add(it) }
                }
                // Populate the Spinner with pet numbers
                val adapter = ArrayAdapter<String>(this@RemovePetsActivity, android.R.layout.simple_spinner_item, petNumbersList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerPetsNo.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Toast.makeText(this@RemovePetsActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun removePet(petsNo: String) {
        databaseReference.child(petsNo).removeValue()
            .addOnSuccessListener {
                val intent = Intent(this@RemovePetsActivity, RemoveSuccessActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to remove pet: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
