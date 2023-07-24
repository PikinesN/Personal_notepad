package com.example.dnevnik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dnevnik.db.MyDbHelper2
import com.example.dnevnik.db.MyDbManager

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)


        val myDbManager = MyDbManager(this)

        myDbManager.openDb_PASSWORD()
        myDbManager.readDb_Password()

        if (myDbManager.readDb_Password().isEmpty()) {
            val a = Intent(this, register::class.java)
            startActivity(a)
            finish()
        }
        else myDbManager.closeDBpassword()



        val login:Button = findViewById(R.id.Login)
        val editTextLogin:EditText = findViewById(R.id.editTextLogin)

        val message = intent.getStringExtra("password")






        login.setOnClickListener {
            var password = ""
            myDbManager.openDb_PASSWORD()
            myDbManager.readDb_Password()
            password = myDbManager.readDb_Password().toString()

            if (password == editTextLogin.text.toString()) {
                val a = Intent(this, MainActivity::class.java)
                startActivity(a)
                finish()
            }
            else {
                Toast.makeText(this, "Неверный пароль", Toast.LENGTH_SHORT).show()
            }

        }

    }
}