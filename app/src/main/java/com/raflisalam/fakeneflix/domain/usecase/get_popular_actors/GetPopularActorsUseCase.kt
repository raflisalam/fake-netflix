package com.raflisalam.fakeneflix.domain.usecase.get_popular_actors

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import kotlinx.coroutines.flow.Flow

interface GetPopularActorsUseCase {

    suspend operator fun invoke(page: Int): Flow<Status<List<Actors>>>
}