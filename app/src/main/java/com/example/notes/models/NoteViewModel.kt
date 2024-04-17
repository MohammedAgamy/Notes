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



   // val allNote:LiveData<List<NoteEntityModel>> = repository.getAllNotes()


   /* init {
        val dao =NoteDataBase.curationDataBase(application).getNoteDao()
        repository!=NoteRepository(dao)
        allNote!=repository!!.allNote

    }
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
    fun search(query:String) = repository.searchNote(query)


}