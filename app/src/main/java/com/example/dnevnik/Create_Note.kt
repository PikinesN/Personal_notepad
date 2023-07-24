package com.example.dnevnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dnevnik.db.MyDbManager
import com.example.dnevnik.db.MyintentConstant

class Create_Note : AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)



        getNyIntents()


        val save:Button = findViewById(R.id.save)
        val AEditText: EditText = findViewById(R.id.title_zametki)
        val BEditText: EditText = findViewById(R.id.opisanie_zametki)

        if (AEditText.text.toString() == "") {
            BEditText.setText("")
        }


        save.setOnClickListener {

            if (proverka(AEditText,BEditText)) {
                Toast.makeText(this, "поля заполни", Toast.LENGTH_SHORT).show()
            }
            else {
                myDbManager.openDb()
                myDbManager.insertToDb(AEditText.text.toString(),BEditText.text.toString())
                myDbManager.closeDB()

                val a = Intent(this, MainActivity::class.java)
                startActivity(a)
                finish()
            }


        }

    }
    private fun proverka(a:EditText,b:EditText):Boolean {


        if (a.text.isNullOrEmpty()) {
            a.error = "Поле должно быть заполнено"
        }
        if (b.text.isNullOrEmpty()) {
            b.error = "Поле должно быть заполнено"
        }

        return a.text.isNullOrEmpty() || b.text.isNullOrEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDB()
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
    }

    fun getNyIntents() {

        val AEditText: EditText = findViewById(R.id.title_zametki)
        val BEditText: EditText = findViewById(R.id.opisanie_zametki)

        val i = intent
        if (i != null) {
            if (i.getStringExtra(MyintentConstant.I_TITLE_KEY) != "null") {
                AEditText.setText(i.getStringExtra("previeA"))
                BEditText.setText(i.getStringExtra("previeB").toString())
            }
        }
    }


}