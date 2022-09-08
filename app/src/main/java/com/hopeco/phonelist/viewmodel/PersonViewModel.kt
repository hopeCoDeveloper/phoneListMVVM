package com.hopeco.phonelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopeco.phonelist.data.model.Person
import com.hopeco.phonelist.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
   private val personRepository: PersonRepository
) : ViewModel() {

    fun getAllPeople() : LiveData<List<Person>>
    {
        return personRepository.getAllPeople()
    }

    fun insertPerson(person: Person)
    {
        viewModelScope.launch {
            personRepository.insertPerson(person)
        }
    }
    fun deletePerson(person: Person)
    {
        viewModelScope.launch {
            personRepository.deletePerson(person)
        }
    }
    fun updatePerson(person: Person)
    {
        viewModelScope.launch {
            personRepository.updatePerson(person)
        }
    }

}