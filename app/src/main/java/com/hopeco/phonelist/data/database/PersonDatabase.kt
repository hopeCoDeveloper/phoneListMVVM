package com.hopeco.phonelist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hopeco.phonelist.data.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun personDao() : PersonDao
}