package com.example.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: CharacterData)

    @Update
    suspend fun update(character: CharacterData)

    @Query("SELECT * from items WHERE id = :id")
    fun getCharacter(id: Int): Flow<CharacterData>

    @Query("SELECT * from items")
    fun getAllCharacters(): Flow<List<CharacterData>>

}