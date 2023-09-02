package com.raflisalam.fakeneflix.domain.usecase.get_detail_actors.get_movie_credits_actors

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits
import kotlinx.coroutines.flow.Flow

interface GetMovieCreditsActorsUseCase {

    suspend operator fun invoke(personId: Int): Flow<Status<List<ActorsMovieCredits>>>
}