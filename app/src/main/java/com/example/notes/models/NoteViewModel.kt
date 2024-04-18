package com.example.notes.models

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.database.NoteDataBase
import com.example.notes.database.NoteRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application,private val repository: NoteRepository):AndroidViewModel(application) {


    /*used fun to add and delete , update , search and getAll data
    from room data base
    i used coroutine to to run multiple tasks
    */
    fun addNote(entityModel: NoteEntityModel) = viewModelScope.launch {

        repository.insert(entityModel)
    }

    fun update(entityModel: NoteEntityModel) = viewModelScope.launch {

        repository.update(entityModel)
    }
    fun delete(entityModel: NoteEntityModel) = viewModelScope.launch {

        repository.delete(entityModel)
    }

    fun getAllNotes() = repository.getAllNotes()
    fun search(query:String?) = repository.searchNote(query)

  /*  A ViewModel is a class specifically designed for use within the Model-View-ViewModel (MVVM) architectural pattern
    for user interfaces in applications. Here's a breakdown of its role:

    Purpose:

    Acts as an intermediary between the View (UI) and the Model (data layer) in an MVVM application.
    Holds the data to be displayed by the View and exposes it in a way that's easy for the View to understand and bind to.
    Handles the presentation logic related to formatting or manipulating data for display.
    Can communicate with the Model to fetch or update data when needed.




    Coroutines are a powerful concurrency mechanism in programming that allow you to write code that appears to run multiple tasks simultaneously,
     even within a single thread. Here's a breakdown of what they are and how they work:



    */
}