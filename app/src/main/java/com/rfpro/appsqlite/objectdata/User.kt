package com.rfpro.appsqlite.objectdata

class User {
    var id:Int = 0
    var name:String = ""
    var ege:Int = 0

    constructor(name:String, ege:Int){
        this.name = name;
        this.ege = ege;
    }

    constructor(){

    }
}