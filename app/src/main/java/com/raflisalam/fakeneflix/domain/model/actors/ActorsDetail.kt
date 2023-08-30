package com.raflisalam.fakeneflix.domain.model.actors

import com.raflisalam.fakeneflix.data.remote.model.actors.MovieCredits


data class ActorsDetail(
    val id: Int,
    val gender: Int,
    val birthday: String,
    val deathDay: String,
    val movie_credits: MovieCredits,
    val name: String,
    val place_of_birth: String,
    val bio: String,
    val profile_path: String
)