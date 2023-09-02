package com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_popular

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTvShowsUseCaseImpl @Inject constructor(
    private val repository: TvShowsRepository
): GetPopularTvShowsUseCase {

    override suspend fun invoke(page: Int): Flow<Status<List<TvShows>>> {
        return repository.getPopularTvShows(page)
    }
}