package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.NoteEntityModel

@Dao
interface NoteDao {


    @Insert
    suspend fun insert(noteEntityModel: NoteEntityModel)

    @Delete
    suspend fun delete(noteEntityModel: NoteEntityModel)

    @Query("UPDATE notes_table SET title= :title, note=:note WHERE id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)

    @Query("select * from notes_table order by id ASC")
    fun getAllNote(): LiveData<List<NoteEntityModel>>
}