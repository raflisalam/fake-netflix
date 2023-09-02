package com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_credits_movie

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.credits.CastMovies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCreditsMovieUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetCreditsMovieUseCase {

    override suspend fun invoke(movieId: Int): Flow<Status<List<CastMovies>>> {
        return repository.getCreditsActorById(movieId)
    }
}