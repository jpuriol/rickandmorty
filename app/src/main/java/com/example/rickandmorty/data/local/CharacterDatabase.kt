package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDAO(): CharacterDAO

    companion object {
        private var instance: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            return instance ?: synchronized(this) {
                Room.inMemoryDatabaseBuilder(context, CharacterDatabase::class.java)
                    .build()
                    .also { instance = it }
            }
        }
    }
}