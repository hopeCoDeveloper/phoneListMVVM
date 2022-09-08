package com.hopeco.phonelist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val family:String,
    val phoneNum : String,
    val address : String
)
