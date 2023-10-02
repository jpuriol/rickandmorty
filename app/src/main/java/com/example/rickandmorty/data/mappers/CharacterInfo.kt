package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.local.CharacterData
import com.example.rickandmorty.domain.CharacterInfo

fun CharacterInfo.toCharacteData(): CharacterData {
    return CharacterData(
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