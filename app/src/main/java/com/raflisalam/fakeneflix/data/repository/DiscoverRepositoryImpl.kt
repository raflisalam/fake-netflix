package com.raflisalam.fakeneflix.data.repository

import com.raflisalam.fakeneflix.common.exception.ApiException
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverMovieNetwork
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverTvShowNetwork
import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import com.raflisalam.fakeneflix.domain.repository.DiscoverRepository
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val apiService: MoviesApi
) : DiscoverRepository {


    override suspend fun getMoviesByGenre(genreId: ArrayList<String>): List<DiscoverMovieNetwork> {
        val response = apiService.discoverMoviesByGenre(genreId)
        if (response.isSuccessful) {
            val moviesResponse = response.body()
            return moviesResponse?.results ?: emptyList()
        } else {
            throw ApiException("Failed to fetch discover movie api")
        }
    }

    override suspend fun getTVShowsByGenre(genreId: ArrayList<String>): List<DiscoverTvShowNetwork> {
        val response = apiService.discoverTvShowsByGenre(genreId)
        if (response.isSuccessful) {
            val tvShowResponse = response.body()
            return tvShowResponse?.results ?: emptyList()
        } else {
            throw ApiException("Failed to fetch discover tv shows api")
        }
    }
}