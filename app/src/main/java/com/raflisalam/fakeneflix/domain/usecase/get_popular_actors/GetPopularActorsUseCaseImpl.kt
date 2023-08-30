package com.raflisalam.fakeneflix.domain.usecase.get_popular_actors

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularActorsUseCaseImpl @Inject constructor(
    private val repository: ActorsRepository
): GetPopularActorsUseCase {

    override suspend fun invoke(page: Int): Flow<Status<List<Actors>>> {
        return repository.getPopularActors(page)
    }
}