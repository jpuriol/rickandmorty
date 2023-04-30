package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.remote.CharacterResult
import com.example.rickandmorty.ui.components.CharacterInfo

fun CharacterResult.toCharacterInfo(): CharacterInfo {
    return CharacterInfo(
        id = id,
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