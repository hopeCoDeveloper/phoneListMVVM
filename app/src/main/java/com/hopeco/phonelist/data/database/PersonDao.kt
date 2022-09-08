package com.hopeco.phonelist.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hopeco.phonelist.data.model.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)


    @Update
    suspend fun updatePerson(person: Person)

    @Query("SELECT * FROM person_table")
    fun getAllPeople() : LiveData<List<Person>>


}