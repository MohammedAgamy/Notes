package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.NoteEntityModel

@Dao
interface NoteDao {
    // dao to used create operation in database like insert update delete
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntityModel: NoteEntityModel)
    @Update
    suspend fun update(noteEntityModel: NoteEntityModel)

    @Delete
    suspend fun delete(noteEntityModel: NoteEntityModel)


    @Query("select * from notes_table order by id desc")
     fun getAllNotes():LiveData<List<NoteEntityModel>>


    @Query("select * from notes_table where title like :query Or note like:query")
     fun search(query:String?): LiveData<List<NoteEntityModel>>
}