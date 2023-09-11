package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverMovieNetwork
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverTvShowNetwork
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult

interface DiscoverRepository {
    suspend fun getMoviesByGenre(genreId: ArrayList<String>): List<DiscoverMovieNetwork>
    suspend fun getTVShowsByGenre(genreId: ArrayList<String>): List<DiscoverTvShowNetwork>
}