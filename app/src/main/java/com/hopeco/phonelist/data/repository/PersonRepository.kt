package com.hopeco.phonelist.data.repository

import androidx.lifecycle.LiveData
import com.hopeco.phonelist.data.database.PersonDao
import com.hopeco.phonelist.data.model.Person
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao: PersonDao
) {
    fun getAllPeople() : LiveData<List<Person>>
    {
        return personDao.getAllPeople()
    }

    suspend fun insertPerson(person: Person)
    {
        personDao.insertPerson(person)
    }
    suspend fun deletePerson(person: Person)
    {
        personDao.deletePerson(person)
    }
    suspend fun updatePerson(person: Person)
    {
        personDao.updatePerson(person)
    }

}