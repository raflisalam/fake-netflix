package com.raflisalam.fakeneflix.domain.usecase.get_nowplaying

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetNowPlayingMoviesUseCase {
    override suspend fun invoke(page: Int): Flow<Status<List<Movies>>> {
        return repository.getNowPlayingMovies(page)
    }
}