package com.example.notes.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Entity(tableName = "notes_table")
@Parcelize
data class NoteEntityModel(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "note") val note:String,
   // @ColumnInfo(name = "date") val date:String
): Parcelable
