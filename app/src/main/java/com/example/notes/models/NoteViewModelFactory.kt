package com.example.notes.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.NoteRepository

class NoteViewModelFactory(private val application: Application, private val repository: NoteRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(application, repository) as T
    }


    /*
    * In the context of Android development using MVVM, ViewModelProvider.Factory is an interface that
      plays a crucial role in creating and managing ViewModels. Here's a breakdown of its purpose and usage:
    * Purpose:
      By default, ViewModelProvider can only instantiate ViewModels with empty constructors.
      ViewModelProvider.Factory provides a way to customize how ViewModels are created, especially when they require dependencies in their constructors.

    * How it Works:
    You implement the ViewModelProvider.Factory interface.
    Inside the implementation, override the create(modelClass: Class<T>, extras: CreationExtras) method.
    Inside create, you take the modelClass (the class of the ViewModel you want to create) and any optional extras (used for advanced scenarios).
    You then create a new instance of the ViewModel, potentially injecting dependencies through its constructor.
    Finally, you return the newly created ViewModel instance.
    * */
}