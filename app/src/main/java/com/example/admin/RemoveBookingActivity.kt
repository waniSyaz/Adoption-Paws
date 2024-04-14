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

class RemoveBookingActivity : AppCompatActivity() {
    //declare component
    private lateinit var spinnerBooking: Spinner
    private lateinit var removeButton: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_booking)
        //initialize component
        spinnerBooking = findViewById(R.id.spinnerBooking)
        removeButton = findViewById(R.id.remove2)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Booking")

        // Fetch pet numbers from Firebase and populate the Spinner
        fetchFullName()

        removeButton.setOnClickListener {
            // Get the selected pet number from the Spinner
            val selectedBooking = spinnerBooking.selectedItem.toString()

            // Remove the selected pet from Firebase
            removeBooking(selectedBooking)

        }
    }

    private fun fetchFullName() {
        val bookingList = mutableListOf<String>()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val fullName = snapshot.key
                    fullName?.let { bookingList.add(it) }
                }
                // Populate the Spinner with pet numbers
                val adapter = ArrayAdapter<String>(this@RemoveBookingActivity, android.R.layout.simple_spinner_item, bookingList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerBooking.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                Toast.makeText(this@RemoveBookingActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun removeBooking(userIc: String) {
        databaseReference.child(userIc).removeValue()
            .addOnSuccessListener {
                val intent = Intent(this@RemoveBookingActivity, RemoveSuccessActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to remove pet: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}


