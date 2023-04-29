package com.example.rickandmorty.remote

import com.example.rickandmorty.ui.components.CharacterInfo
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
    fun toCharacter(): CharacterInfo {
        return CharacterInfo(
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
            origin = origin.name,
            location = location.name,
            imageURL = image,
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
