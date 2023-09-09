package com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_recommendations

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationsTvShowsUseCaseImpl @Inject constructor(
    private val repository: TvShowsRepository
): GetRecommendationsTvShowsUseCase {

    override suspend fun invoke(seriesId: Int, page: Int): Flow<Status<List<TvShows>>> {
        return repository.getRecommendationsTvShows(seriesId, page)
    }
}