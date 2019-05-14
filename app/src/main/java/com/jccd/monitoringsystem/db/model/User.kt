package com.jccd.monitoringsystem.db.model

data class User(
    val fullName: String,
    val email:String,
    val cellPhone:String
){
    var uid = ""
}