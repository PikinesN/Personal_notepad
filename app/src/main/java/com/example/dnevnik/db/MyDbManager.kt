package com.example.dnevnik.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class MyDbManager (val context: Context) {
    val myDbHElper = MyDbHelper(context)
    var db:SQLiteDatabase? = null


    val myDbHElper2 = MyDbHelper2(context)



    fun openDb(){
        db = myDbHElper.writableDatabase
    }

    fun openDb_PASSWORD(){
        db = myDbHElper2.writableDatabase
    }

    fun insertToDbpassword(password:String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_PASSWORD,password)
        }
        db?.insert(MyDBNameClass.TABLE_NAME_PASSWORD,null,values)
    }

    fun insertToDb(title:String, desk:String) {
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE,title)
            put(MyDBNameClass.COLUMN_NAME_DESCRIPTION,desk)
        }
        db?.insert(MyDBNameClass.TABLE_NAME,null,values)
    }

    fun renoveItemFromDB(ID:String) {

        val selection = BaseColumns._ID + "=$ID"
        db?.delete(MyDBNameClass.TABLE_NAME,selection, null)
    }

    fun readDb_Password(): String {
        var password:String = ""

        val cursor = db?.query(
            MyDBNameClass.TABLE_NAME_PASSWORD,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataText = cursor?.getString(1)
                password = dataText.toString()
            }
        }

        cursor?.close()
        return password
    }

    fun readDbData(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()

        val cursor = db?.query(
            MyDBNameClass.TABLE_NAME,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        with(cursor) {
            while (this?.moveToNext()!!) {
                val dataTitl = cursor?.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_TITLE))
                val dataContent = cursor?.getString(cursor.getColumnIndexOrThrow(MyDBNameClass.COLUMN_NAME_DESCRIPTION))
                val dataid = cursor?.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))

                val item = ListItem()
                item.title = dataTitl.toString()
                item.desc = dataContent.toString()
                item.id = dataid!!.toInt()
                dataList.add(item)
            }
        }

        cursor?.close()
        return dataList
    }

    fun closeDB () {
        myDbHElper.close()
    }
    fun closeDBpassword () {
        myDbHElper2.close()
    }
}