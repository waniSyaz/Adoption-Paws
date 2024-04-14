package com.example.adoptionpets

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class BookingActivity : AppCompatActivity() {
    // Declare all components
    private lateinit var spinnerPetsNo: Spinner
    private lateinit var arrow: ImageButton
    private lateinit var booking: Button
    private lateinit var cat: RadioButton
    private lateinit var dog: RadioButton
    private lateinit var pets: EditText
    private lateinit var name: EditText
    private lateinit var ic : EditText

    // Declare firebase
    private lateinit var databaseReference: DatabaseReference

    private var selectedPetType: String = "" // Variable to store the selected pet type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        // Initialize all components
        spinnerPetsNo = findViewById(R.id.spinnerPetsNo)
        arrow = findViewById(R.id.btnArrow)
        booking = findViewById(R.id.btnBook)
        cat = findViewById(R.id.catBtn)
        dog = findViewById(R.id.dogBtn)
        pets = findViewById(R.id.eTpet)
        name = findViewById(R.id.eTname)
        ic = findViewById(R.id.eTIC)

        //Declare database
        databaseReference = FirebaseDatabase.getInstance().reference.child("Pets")

        // Fetch pet numbers from Firebase and populate the Spinner
        fetchPetNumbers()

        // Set click listeners for the pet type buttons
        cat.setOnClickListener {
            selectedPetType = "Cat"
            dog.isChecked = false // Uncheck the dog button
        }

        dog.setOnClickListener {
            selectedPetType = "Dog"
            cat.isChecked = false // Uncheck the cat button
        }

        // Set listener for spinner selection
        spinnerPetsNo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPetNo = parent?.getItemAtPosition(position).toString()
                // Fetch details for the selected pet number from Firebase
                fetchPetDetails(selectedPetNo)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        booking.setOnClickListener {
            val selectedPetsNo = spinnerPetsNo.selectedItem.toString()
            val pName = pets.text.toString()
            val userIc = ic.text.toString()
            val uName = name.text.toString()

            if (selectedPetsNo.isEmpty()) {
                showToast("Please select the number of pets")
            } else if (pName.isEmpty()) {
                showToast("Please fill the pet's name field")
            } else if (uName.isEmpty()) {
                showToast("Please fill the name field")
            } else if (selectedPetType.isEmpty()) {
                showToast("Please select a pet type")
            } else if (userIc.isEmpty()) {
                showToast("Please fill the IC field")
            } else {
                saveData(userIc,selectedPetsNo, selectedPetType, pName, uName)
            }
        }

        arrow.setOnClickListener {
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
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
                val adapter = ArrayAdapter<String>(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    petNumbersList
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerPetsNo.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun fetchPetDetails(selectedPetNo: String) {
        databaseReference.child(selectedPetNo).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val petDetails = dataSnapshot.getValue(Pets::class.java)
                petDetails?.let {
                    // Update UI with fetched pet details
                    pets.setText(it.petsName)
                    // Update selectedPetType based on fetched pet type
                    selectedPetType = it.petsType.toString()
                    if (it.petsType == "Cat") {
                        cat.isChecked = true
                        dog.isChecked = false
                    } else if (it.petsType == "Dog") {
                        dog.isChecked = true
                        cat.isChecked = false
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
    }

    private fun saveData(userIc: String, no: String, t: String, p: String, nm: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Booking")

        // Use userIc as the key for the Booking object
        val em = Booking(no, t, p, nm, userIc)

        // Set the value of the child node with the specified userIc
        databaseReference.child(userIc).setValue(em)
            .addOnCompleteListener {
                val i = Intent(this, DoneActivity::class.java)
                startActivity(i)
            }.addOnFailureListener {
                showToast("Failure")
            }
    }



    private fun showToast(message: String) {
        Toast.makeText(this@BookingActivity, message, Toast.LENGTH_SHORT).show()
    }
}
