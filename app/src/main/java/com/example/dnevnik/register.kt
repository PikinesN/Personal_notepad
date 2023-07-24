package com.example.dnevnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dnevnik.db.MyDbManager

class register : AppCompatActivity() {

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val button:Button = findViewById(R.id.button)
        val editText:EditText = findViewById(R.id.editText)


        button.setOnClickListener {


            myDbManager.openDb_PASSWORD()
            myDbManager.insertToDbpassword(editText.text.toString())
            val password = myDbManager.readDb_Password()
            myDbManager.closeDB()


            val a = Intent(this, Login::class.java)
            a.putExtra("password", password)
            startActivity(a)


            finish()


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager
    }
}