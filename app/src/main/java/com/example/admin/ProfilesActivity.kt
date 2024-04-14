package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class ProfilesActivity : AppCompatActivity() {

    private lateinit var add : ImageButton
    private lateinit var remove : ImageButton
    private lateinit var edit : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profiles)

        add = findViewById(R.id.addProfiles)
        remove = findViewById(R.id.removeProfiles)
        edit = findViewById(R.id.editProfiles)

        add.setOnClickListener{
            val intent = Intent(this@ProfilesActivity, AddPetActivity::class.java)
            startActivity(intent)
        }

        remove.setOnClickListener {
            val intent = Intent(this@ProfilesActivity, RemovePetsActivity::class.java)
            startActivity(intent)
        }

        edit.setOnClickListener{
            val intent = Intent(this@ProfilesActivity, EditPetActivity::class.java)
            startActivity(intent)
        }
    }
}