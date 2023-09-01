package com.raflisalam.fakeneflix.data.remote.model.actors

import com.raflisalam.fakeneflix.domain.model.actors.ActorsDetail

data class ActorsDetailsDto(
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: Any,
    val gender: Int,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val known_for_department: String,
    val movie_credits: MovieCredits,
    val name: String,
    val place_of_birth: String,
    val popularity: Double,
    val profile_path: String
)

fun ActorsDetailsDto.toActorsDetail(): ActorsDetail {
    return ActorsDetail(
        personId = id,
        gender = gender,
        birthday = birthday,
        bio = biography,
        movie_credits = movie_credits,
        name = name,
        place_of_birth = place_of_birth,
        profile_path = profile_path
    )
}