package com.example.notes.database

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
//we dont use it
class SharedPreferenceManager(context: Context) {

    val preferenceManager =context.getSharedPreferences("ChangedMode",AppCompatActivity.MODE_PRIVATE)
    private val editor = preferenceManager.edit()

    val keyTheme ="theme"

    var theme
        get() =preferenceManager.getInt(keyTheme ,2)
        set(value) {
            editor.putInt(keyTheme,value)
            editor.commit()
        }


    val themeFlag = arrayOf(
        AppCompatDelegate.MODE_NIGHT_NO,
        AppCompatDelegate.MODE_NIGHT_YES,
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

    )
}