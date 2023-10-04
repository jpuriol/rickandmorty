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
    suspend fun insert(character: CharacterEntity)

    @Update
    suspend fun update(character: CharacterEntity)

    @Query("SELECT * from items WHERE id = :id")
    fun getCharacter(id: Int): Flow<CharacterEntity>

    @Query("SELECT * from items ORDER BY id ASC")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

}