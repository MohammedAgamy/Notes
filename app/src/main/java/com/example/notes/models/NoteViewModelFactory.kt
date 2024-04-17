package com.example.notes.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.NoteRepository

class NoteViewModelFactory(private val application: Application, private val repository: NoteRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application ,repository) as T
    }
}