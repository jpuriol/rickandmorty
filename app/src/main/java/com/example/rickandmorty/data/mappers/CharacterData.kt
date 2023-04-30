package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.local.CharacterData
import com.example.rickandmorty.data.remote.CharacterResult
import com.example.rickandmorty.ui.components.CharacterInfo

fun CharacterResult.toCharacterData(): CharacterData {
    return CharacterData(
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

fun CharacterData.toCharacterInfo(): CharacterInfo {
    return CharacterInfo(
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