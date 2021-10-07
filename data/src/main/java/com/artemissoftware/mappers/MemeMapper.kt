package com.artemissoftware.mappers

import com.artemissoftware.domain.model.Meme
import com.artemissoftware.remote.dto.MemeDto

fun MemeDto.toMeme(): Meme {

    return Meme(
        url = url,
        width = width,
        height = height,
    )
}