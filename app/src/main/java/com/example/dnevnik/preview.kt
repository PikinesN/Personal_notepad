package com.example.dnevnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.example.dnevnik.db.MyintentConstant
import com.google.android.material.floatingactionbutton.FloatingActionButton

class preview : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        getNyIntents()

        val editButton:FloatingActionButton = findViewById(R.id.edit_preview)

        val AEditText:TextView = findViewById(R.id.titlepreview)
        val BEditText:TextView = findViewById(R.id.descriptionpreview)

        BEditText.movementMethod = ScrollingMovementMethod.getInstance()

        editButton.setOnClickListener {

            val i = Intent(this, Create_Note::class.java)
            i.apply {
                putExtra("previeA", AEditText.text.toString())
                putExtra("previeB", BEditText.text.toString())
            }
            startActivity(i)

        }
    }


    fun getNyIntents() {

        val AEditText:TextView = findViewById(R.id.titlepreview)
        val BEditText:TextView = findViewById(R.id.descriptionpreview)

        val i = intent
        if (i != null) {
            if (i.getStringExtra(MyintentConstant.I_TITLE_KEY) != null) {
                AEditText.text = i.getStringExtra(MyintentConstant.I_TITLE_KEY).toString()
                BEditText.text = i.getStringExtra(MyintentConstant.I_DISC_KEY).toString()
            }
        }
    }

}