package com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_recommendations

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import kotlinx.coroutines.flow.Flow

interface GetRecommendationsTvShowsUseCase {
    suspend operator fun invoke(seriesId: Int, page: Int): Flow<Status<List<TvShows>>>
}