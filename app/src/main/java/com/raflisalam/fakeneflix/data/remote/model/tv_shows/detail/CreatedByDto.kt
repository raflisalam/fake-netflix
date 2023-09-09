package com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail

import com.raflisalam.fakeneflix.domain.model.tv_shows.CreatedBy

data class CreatedByDto(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
)

fun CreatedByDto.toCreatedBy(): CreatedBy {
    return CreatedBy(
        id = id,
        name = name,
        profilePics = profile_path
    )
}