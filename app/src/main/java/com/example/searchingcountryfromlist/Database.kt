package com.example.searchingcountryfromlist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
val ctrydatabase="dbName"
val ctrytable="dbTable"
val ctryname="NAME"
val ctryregion="REGION"
var ctryflag="FLAG"
val ctryalphacod="ALPHACOD"
val ctrycapital="CAPITAL"
val ctryid="ID"
val versiondb=1
class Database(context: Context):SQLiteOpenHelper(context, ctrydatabase,null, versiondb) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createsqlTable = "CREATE TABLE IF NOT EXISTS " + ctrytable + " (" + ctryid + " INTEGER PRIMARY KEY," + ctryname + " TEXT," + ctryalphacod + " TEXT," + ctrycapital + " TEXT," + ctryflag + " BLOB," + ctryregion + " TEXT);"
       db?.execSQL(createsqlTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("if database is exsits" + ctrytable)
    }

    fun Insert(values:ContentValues):Long{
           var sqLiteDatabase:SQLiteDatabase=this.writableDatabase
           var dblong=sqLiteDatabase.insert(ctrytable,null,values)
           return dblong
    }
    fun Query():Cursor{
        var sqLiteDatabase:SQLiteDatabase=this.writableDatabase
        var cursor=sqLiteDatabase.rawQuery("SELECT * FROM " + ctrytable,null)
        return cursor
    }
}