package com.example.rickandmorty.remote

import com.example.rickandmorty.ui.screens.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResult(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toCharacter(): Character {
        return Character(
            name = name,
            imageURL = image,
            status = status,
            species = species,
            origin = origin.name,
        )
    }

}

@Serializable
data class Location(
    val name: String,
    val url: String
)

@Serializable
data class Origin(
    val name: String,
    val url: String
)
