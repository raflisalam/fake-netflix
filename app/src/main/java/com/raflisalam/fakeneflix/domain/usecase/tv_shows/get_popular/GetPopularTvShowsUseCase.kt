package com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import kotlinx.coroutines.flow.Flow


interface GetPopularTvShowsUseCase {
    suspend operator fun invoke (page: Int): Flow<Status<List<TvShows>>>
}