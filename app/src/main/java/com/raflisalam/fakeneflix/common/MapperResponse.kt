package com.raflisalam.fakeneflix.common

import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsResponse
import com.raflisalam.fakeneflix.data.remote.model.actors.MovieCredits
import com.raflisalam.fakeneflix.data.remote.model.movies.MoviesResponse
import com.raflisalam.fakeneflix.data.remote.model.credits.CreditsMovie
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.TvShowsResponse
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits
import com.raflisalam.fakeneflix.domain.model.credits.CastMovies
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows

fun getResponseMovieToModel(response: MoviesResponse?): List<Movies> {
    val movieList = mutableListOf<Movies>()

    response?.results?.forEach { data ->
        val movie = Movies(
            id = data.id ?: 0,
            genreId = data.genre_ids ?: emptyList(),
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

fun getResponseCreditsCastToModel(response: CreditsMovie?): List<CastMovies> {
    val castMoviesList = mutableListOf<CastMovies>()

    response?.cast?.forEach { data ->
        val castMovies = CastMovies(
            id = data.id ?: 0,
            name = data.name ?: "",
            profilePics = data.profile_path ?: "",
            nameCharacter = data.character ?: ""
        )
        castMoviesList.add(castMovies)
    }
    return castMoviesList
}

fun getResponseMovieCreditsToModel(response: MovieCredits?): List<ActorsMovieCredits> {
    val movieCreditsList = mutableListOf<ActorsMovieCredits>()

    response?.cast?.forEach { data ->
        val movieCredits = ActorsMovieCredits(
            moviesId = data.id ?: 0,
            characterName = data.character ?: "",
            moviesName = data.original_title ?: "",
            moviesPoster = data.poster_path ?: ""
        )
        movieCreditsList.add(movieCredits)
    }
    return movieCreditsList
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

fun getResponseTvShowsToModel(response: TvShowsResponse?): List<TvShows> {
    val tvShowsList = mutableListOf<TvShows>()

    response?.results?.forEach {  data ->
        val tvShows = TvShows(
            tvShowsId = data.id ?: 0,
            titleTvShows = data.name ?: "",
            background_poster = data.backdrop_path ?: "",
            release_date = data.first_air_date ?: "",
            genresId = data.genre_ids ?: emptyList(),
            image_poster = data.poster_path ?: "",
            description = data.overview ?: "",
            rating = data.vote_count ?: 0
        )
        tvShowsList.add(tvShows)
    }
    return tvShowsList
}
