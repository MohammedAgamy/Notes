package com.example.notes.database

import androidx.lifecycle.LiveData
import com.example.notes.models.NoteEntityModel

class NoteRepository(private val dao: NoteDataBase) {
    //al allNote:List<NoteEntityModel> =dao.getAllNote()

    suspend fun insert(noteEntityModel: NoteEntityModel) {
        dao.getNoteDao().insert(noteEntityModel)
    }

    suspend fun delete(noteEntityModel: NoteEntityModel) {
        dao.getNoteDao().delete(noteEntityModel)
    }

    suspend fun update(noteEntityModel: NoteEntityModel) = dao.getNoteDao().update(noteEntityModel)



    fun getAllNotes() = dao.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = dao.getNoteDao().search(query)
}