package com.raflisalam.fakeneflix.common

import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsResponse
import com.raflisalam.fakeneflix.data.remote.model.movies.MoviesResponse
import com.raflisalam.fakeneflix.data.remote.model.credits.MovieCredits
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.credits.Cast
import com.raflisalam.fakeneflix.domain.model.movies.Movies

fun getResponseMovieToModel(response: MoviesResponse?): List<Movies> {
    val movieList = mutableListOf<Movies>()

    response?.results?.forEach { data ->
        val movie = Movies(
            id = data.id ?: 0,
            title = data.title ?: "Unknown movie",
            poster = data.poster_path ?: "",
            background = data.backdrop_path ?: "Unknown Movie",
            description = data.overview ?: "Unknown Description",
            release_date = data.release_date ?: "Unknown Date",
            rating = data.vote_average ?: 0.0
        )
        movieList.add(movie)
    }
    return movieList
}

fun getResponseCreditsCastToModel(response: MovieCredits?): List<Cast> {
    val castList = mutableListOf<Cast>()

    response?.cast?.forEach { data ->
        val cast = Cast(
            id = data.id ?: 0,
            name = data.name ?: "",
            profilePics = data.profile_path ?: "",
            nameCharacter = data.character ?: ""
        )
        castList.add(cast)
    }
    return castList
}

fun getResponseActorsToModel(response: ActorsResponse?): List<Actors> {
    val actorsList = mutableListOf<Actors>()

    response?.results?.forEach { data ->
        val actors = Actors(
            id = data.id ?: 0,
            name = data.name ?: "",
            profilePath = data.profile_path ?: ""
        )
        actorsList.add(actors)
    }
    return actorsList
}