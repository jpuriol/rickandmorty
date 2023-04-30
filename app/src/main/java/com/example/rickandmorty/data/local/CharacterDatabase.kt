package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterData::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDAO(): CharacterDAO

    companion object {
        private var instance: CharacterDatabase? = null

        fun getDatabase(contex: Context): CharacterDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(contex, CharacterDatabase::class.java, "character_database")
                    .build()
                    .also { instance = it }
            }
        }
    }
}