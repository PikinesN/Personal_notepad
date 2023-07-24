package com.example.dnevnik

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dnevnik.adpter.MyAdapter
import com.example.dnevnik.db.MyDbManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView:TextView = findViewById(R.id.text_welcome)
        myDbManager.openDb()
        if (myDbManager.readDbData().isEmpty()) {
            textView.visibility = View.VISIBLE
        }
        else {
            textView.visibility = View.GONE
        }

         init()


        val add:FloatingActionButton = findViewById(R.id.add)

        add.setOnClickListener {
            val a = Intent(this, Create_Note::class.java)
            startActivity(a)
            finish()
        }

        val tvTitle:TextView? = findViewById(R.id.tv_title)
        tvTitle?.setOnClickListener {
            Toast.makeText(this, "${tvTitle.text}", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDB()
    }
    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    fun fillAdapter(){
        myAdapter.updateAdapter(myDbManager.readDbData())
    }

    fun init() {
        val recyclerView: RecyclerView = findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = myAdapter
    }

    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }

}