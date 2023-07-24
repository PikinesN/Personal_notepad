package com.example.dnevnik.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context): SQLiteOpenHelper(context, MyDBNameClass.TABLE_NAME, null, MyDBNameClass.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(MyDBNameClass.CREAT_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL(MyDBNameClass.SQL_DELETE_ENTRIES)
        onCreate(p0)
    }
}

class MyDbHelper2(context: Context): SQLiteOpenHelper(context, MyDBNameClass.TABLE_NAME_PASSWORD, null, MyDBNameClass.DATABASE_VERSION_PASSWORD) {
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(MyDBNameClass.CREAT_TABLE_PASSWORD)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL(MyDBNameClass.SQL_DELETE_PASSWORD)
        onCreate(p0)
    }
}