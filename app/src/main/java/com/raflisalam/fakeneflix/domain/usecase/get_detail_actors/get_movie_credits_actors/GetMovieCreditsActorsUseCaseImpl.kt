package com.raflisalam.fakeneflix.domain.usecase.get_detail_actors.get_movie_credits_actors

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits
import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditsActorsUseCaseImpl @Inject constructor(
    private val repository: ActorsRepository
): GetMovieCreditsActorsUseCase {

    override suspend fun invoke(personId: Int): Flow<Status<List<ActorsMovieCredits>>> {
        return repository.getMovieCreditsActor(personId)
    }

}