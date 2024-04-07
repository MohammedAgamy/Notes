package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.models.NoteEntityModel
import kotlin.synchronized as synchronized1

@Database(entities = arrayOf(NoteEntityModel::class), version = 1, exportSchema = false)
abstract class NoteDataBase :RoomDatabase(){

    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDataBase?=null

        fun curationDataBase(context: Context) :NoteDataBase{
            return (INSTANCE?: synchronized1(this){
                val instance = Room.databaseBuilder(context.applicationContext,NoteDataBase::class.java,"NoteDataBase").build()
                INSTANCE =instance
                instance
            }) as NoteDataBase
        }
    }
}