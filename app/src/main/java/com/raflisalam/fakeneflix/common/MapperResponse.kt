package com.raflisalam.fakeneflix.common

import com.raflisalam.fakeneflix.data.remote.model.MoviesResponse
import com.raflisalam.fakeneflix.data.remote.model.credits.CastDto
import com.raflisalam.fakeneflix.data.remote.model.credits.MovieCredits
import com.raflisalam.fakeneflix.domain.model.Actor
import com.raflisalam.fakeneflix.domain.model.Movies

fun getResponseMovieToModel(response: MoviesResponse?): List<Movies> {
    val movieList = mutableListOf<Movies>()

    response?.results?.forEach { data ->
        val movie = Movies(
            id = data.id ?: 0,
            title = data.title ?: "Unknown movie",
            poster = data.poster_path ?: "Unknown Movie",
            background = data.backdrop_path ?: "Unknown Movie",
            description = data.overview ?: "Unknown Description",
            release_date = data.release_date ?: "Unknown Date",
            rating = data.vote_average ?: 0.0
        )
        movieList.add(movie)
    }
    return movieList
}

fun getResponseCreditsCastToModel(response: MovieCredits?): List<Actor> {
    val actorList = mutableListOf<Actor>()

    response?.cast?.forEach { data ->
        val actor = Actor(
            name = data.name ?: "Rafli",
            profilePics = data.profile_path ?: "",
            nameCharacter = data.character ?: "Toreto"
        )
        actorList.add(actor)
    }
    return actorList
}