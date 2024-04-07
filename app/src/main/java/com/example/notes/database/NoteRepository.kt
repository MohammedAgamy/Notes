package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.models.NoteEntityModel

class NoteRepository (private val dao: NoteDao){
    val allNote:LiveData<List<NoteEntityModel>> =dao.getAllNote()

    suspend fun insert(noteEntityModel: NoteEntityModel)
    {
        dao.insert(noteEntityModel)
    }

    suspend fun delete(noteEntityModel: NoteEntityModel)
    {
        dao.delete(noteEntityModel)
    }

    suspend fun update(noteEntityModel: NoteEntityModel)
    {
        dao.update(noteEntityModel.id,noteEntityModel.title,noteEntityModel.note)
    }
}