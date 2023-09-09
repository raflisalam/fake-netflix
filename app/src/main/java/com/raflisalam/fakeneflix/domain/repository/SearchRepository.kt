package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.exception.ApiException
import com.raflisalam.fakeneflix.common.exception.MediaTypeNotRecognizedException
import com.raflisalam.fakeneflix.data.remote.datasource.SearchResultDataSource
import com.raflisalam.fakeneflix.domain.model.search.MovieResult
import com.raflisalam.fakeneflix.domain.model.search.PersonResult
import com.raflisalam.fakeneflix.domain.model.search.TvShowResult
import javax.inject.Inject


class SearchRepository @Inject constructor(
    private val dataSource: SearchResultDataSource
) {

    suspend fun fetchSearchResults(query: String): List<Any> {
        val response = dataSource.fetchSearchResults(query)
        if (response.isSuccessful) {
            val data = response.body()?.results ?: emptyList()
            val domainModels = mutableListOf<Any>()

            for (item in data) {
                when (item.media_type) {
                    "movie" -> {
                        val movie = MovieResult(
                            id = item.id,
                            title = item.title,
                            overview = item.overview,
                            genre_ids = item.genre_ids,
                            poster_path = item.poster_path,
                            release_date = item.release_date,
                            vote_average = item.vote_average
                        )
                        domainModels.add(movie)
                    }
                    "tv" -> {
                        val tvShows = TvShowResult(
                            id = item.id,
                            name = item.name,
                            overview = item.overview,
                            genre_ids = item.genre_ids,
                            poster_path = item.poster_path,
                            first_air_date = item.first_air_date,
                            vote_average = item.vote_average
                        )
                        domainModels.add(tvShows)
                    }
                    "person" -> {
                        val person = PersonResult(
                            id = item.id,
                            name = item.name,
                            gender = item.gender,
                            profile_path = item.profile_path
                        )
                        domainModels.add(person)
                    }
                    else -> {
                        throw MediaTypeNotRecognizedException("MediaType: ${item.media_type} is invalid")
                    }
                }
            }
            return domainModels
        } else {
            throw ApiException("Failed to fetch data from API")
        }
    }

}