package com.example.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddPetActivity : AppCompatActivity() {

    private lateinit var no : EditText
    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var cat : RadioButton
    private lateinit var dog : RadioButton
    private lateinit var breed : EditText
    private lateinit var add : Button
    private lateinit var imageViewPet: ImageView
    private lateinit var buttonSelectImage: Button

    //Declare firebase
    private lateinit var databaseReference : DatabaseReference
    // Variable to store the selected pet type
    private var selectedPetType: String = ""
    // Variable to store the selected pet image
    private var selectedImageUri: Uri? = null

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the selected image URI
                val selectedImageUri = result.data?.data
                // Set the selected image URI to the ImageView
                imageViewPet.setImageURI(selectedImageUri)
                // Store the selected image URI
                this.selectedImageUri = selectedImageUri
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pet)

        //Initialize all component
        no = findViewById(R.id.eTNo)
        name = findViewById(R.id.eTName)
        age = findViewById(R.id.eTAge)
        cat = findViewById<RadioButton>(R.id.buttonCat)
        dog = findViewById<RadioButton>(R.id.buttonDog)
        breed = findViewById(R.id.eTBreed)
        add = findViewById(R.id.Update)

        //Initialize image
        imageViewPet = findViewById(R.id.imageViewPet)
        buttonSelectImage = findViewById(R.id.buttonSelectImage)

        // Set click listeners for the pet type buttons
        cat.setOnClickListener {
            selectedPetType = "Cat"
            dog.isChecked = false // Uncheck the dog button
        }

        dog.setOnClickListener {
            selectedPetType = "Dog"
            cat.isChecked = false // Uncheck the cat button
        }

        buttonSelectImage.setOnClickListener {
            // Open the image gallery
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getContent.launch(intent)
        }

        add.setOnClickListener {
            val no = no.text.toString()
            val name = name.text.toString()
            val age = age.text.toString()
            val breed = breed.text.toString()

            if (no.isEmpty()) {
                showToast("Please fill the number field")
            } else if (name.isEmpty()) {
                showToast("Please fill the pet's name field")
            } else if (age.isEmpty()) {
                showToast("Please fill the pet's age field")
            } else if (selectedPetType.isEmpty()) {
                showToast("Please select a pet type")
            }else if (breed.isEmpty()) {
                showToast("Please select a breed type")
            } else {
                // Call the function to save data
                saveData(no, name, age,selectedPetType,breed)
            }

            val i = Intent(this, SuccessActivity::class.java)
            startActivity(i)
        }

    }

    private fun saveData(petNo: String, n: String, a: String, t: String,b: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Pets")

        val em = Pets(petNo, n, a, t, b)

        databaseReference.child(petNo).setValue(em)
            .addOnCompleteListener {
                showToast("Success")
            }.addOnFailureListener {
                showToast("Failure")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@AddPetActivity, message, Toast.LENGTH_SHORT).show()
    }
}
