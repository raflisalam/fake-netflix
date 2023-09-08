package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.TvShowsDetailsDto
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    suspend fun getPopularTvShows(page: Int): Flow<Status<List<TvShows>>>

    suspend fun getDetailTvShowsById(seriesId: Int): TvShowsDetailsDto
}