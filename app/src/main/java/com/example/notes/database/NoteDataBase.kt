package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.database.NoteDataBase.Companion.INSTANCE
import com.example.notes.models.NoteEntityModel
import kotlin.synchronized as synchronized1

@Database(entities =[NoteEntityModel::class], version = 1)
abstract class NoteDataBase :RoomDatabase(){
// creation Room DataBase
    abstract fun getNoteDao():NoteDao


    // singleTon pattern to creation one Of instance dataBase in our project
    companion object{
        @Volatile
        private var INSTANCE:NoteDataBase?=null
        private val LOCK = Any()

       operator fun invoke(context: Context) = INSTANCE?:
        synchronized1(LOCK){
               INSTANCE?:
               createDataBase(context).also {
                   INSTANCE = it
               }
            }


        private fun createDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,NoteDataBase::class.java,"NoteDataBase").build()



        }


}

