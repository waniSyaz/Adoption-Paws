package com.example.adoptionpets

import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FaqsActivity : AppCompatActivity() {
    //declare component
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: ExAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faqs)

        expandableListView = findViewById(R.id.expandableListView)

        val listData = HashMap<String, List<String>>()
        val questions = mutableListOf<String>()
        val answers = mutableListOf<String>()

        // Add questions and answers
        questions.add("What should I consider before adopting a pet?")
        questions.add("What is the adoption process like?")
        questions.add("What responsibilities come with pet adoption?")
        questions.add("Can I meet the pet before deciding to adopt?")
        questions.add("Can I adopt if I already have pets at home?")

        answers.add("Before adopting a pet, consider factors such as your lifestyle, living space, time commitment, and financial resources required to care for a pet.")
        answers.add("The adoption process typically involves visiting the shelter or rescue organization, meeting available pets, filling out an application, and going through an adoption interview and screening process.")
        answers.add("Pet adoption comes with responsibilities such as providing food, shelter, medical care, exercise, grooming, training, and love and attention to ensure the pet's health and well-being.")
        answers.add("Yes, we encourage potential adopters to spend time with the pet to ensure it's a good fit for both parties.")
        answers.add("Absolutely! We'll work with you to ensure a smooth introduction between your existing pets and your new addition to the family.")

        listData[questions[0]] = listOf(answers[0])
        listData[questions[1]] = listOf(answers[1])
        listData[questions[2]] = listOf(answers[2])
        listData[questions[3]] = listOf(answers[3])
        listData[questions[4]] = listOf(answers[4])

        adapter = ExAdapter(this, questions, listData)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            Toast.makeText(
                applicationContext,
                questions[groupPosition] + " : " + listData[questions[groupPosition]]?.get(childPosition),
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }
}