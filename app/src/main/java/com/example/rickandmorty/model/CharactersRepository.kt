package com.example.rickandmorty.model

import com.example.rickandmorty.data.local.CharacterDAO
import com.example.rickandmorty.data.local.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CharactersRepository(
    private val dao: CharacterDAO,
) {

    fun get(): Flow<List<Character>> {
        return dao.getAllCharacters().map {
            it.map { entity -> entity.toCharacter() }
        }
    }

    suspend fun character(id: Int): Character {
        return dao.getCharacter(id).first().toCharacter()
    }

    suspend fun update(character: Character) {
        dao.update(character.toCharacterEntity())
    }
}

private fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        imageURL = imageURL,
    )
}

private fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin,
        location = location,
        imageURL = imageURL,
    )
}
