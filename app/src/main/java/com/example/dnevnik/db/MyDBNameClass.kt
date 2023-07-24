package com.example.dnevnik.db

import android.provider.BaseColumns

object MyDBNameClass:BaseColumns {
    const val TABLE_NAME = "my_table"
    const val TABLE_NAME_PASSWORD = "password"
    const val COLUMN_PASSWORD = "password"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_DESCRIPTION = "description"

    const val DATABASE_VERSION = 1
    const val DATABASE_VERSION_PASSWORD = 1


    const val CREAT_TABLE =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_TITLE TEXT," +
                "$COLUMN_NAME_DESCRIPTION TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"


    const val CREAT_TABLE_PASSWORD =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME_PASSWORD (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_PASSWORD TEXT)"


    const val SQL_DELETE_PASSWORD = "DROP TABLE IF EXISTS $TABLE_NAME_PASSWORD"


}