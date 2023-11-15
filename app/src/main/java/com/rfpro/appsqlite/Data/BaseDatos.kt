package com.rfpro.appsqlite.Data
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rfpro.appsqlite.objectdata.User

var BD = ""
var sql:String = ""

class BaseDatos(contexto: Context): SQLiteOpenHelper(contexto, BD, null, 1)  {
    override fun onCreate(db: SQLiteDatabase?) {
        sql = "CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(250), ege INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun InsertData(user:User):String {
        val db = this.writableDatabase
        val container = ContentValues()

        container.put("name", user.name)
        container.put("ege", user.ege)

        var result = db.insert("User" ,null, container)

        if (result == 1.toLong()){
            return "fail insert database"
        }else{
            return "insert OK"
        }
    }

    @SuppressLint("Range")
    fun listData():MutableList<User>{
        var list:MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from User"
        val result = db.rawQuery(   sql, null)
        if (result.moveToFirst()){
            do {
                var usu = User()
                usu.id = result.getString(result.getColumnIndex("id")).toInt()
                usu.name = result.getString(result.getColumnIndex("name"))
                usu.ege = result.getString(result.getColumnIndex("id")).toInt()
                list.add(usu)

            }while (result.moveToNext())
            result.close()
            db.close()
        }

        return list;
    }

    fun update(id:String, name:String, ege:Int): String {
        val db = this.writableDatabase
        var containerValues = ContentValues()
        containerValues.put("name", name)
        containerValues.put("ege", ege)
        var result = db.update("User", containerValues, "id = ?", arrayOf(id))

        return if (result > 0){
            "update correct"
        }else{
            "not update"
        }
    }

    fun deleteData(id:String){
        val db = this.writableDatabase
        if (id.isNotEmpty()){
            db.delete("User", "id=?", arrayOf(id))
        }

    }

}