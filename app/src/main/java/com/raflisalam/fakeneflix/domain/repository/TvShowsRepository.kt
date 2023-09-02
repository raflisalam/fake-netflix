package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun getPopularTvShows(page: Int): Flow<Status<List<TvShows>>>
}