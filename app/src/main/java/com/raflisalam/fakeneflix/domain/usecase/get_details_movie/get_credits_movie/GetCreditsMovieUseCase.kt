package com.raflisalam.fakeneflix.domain.usecase.get_details_movie.get_credits_movie

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Actor
import kotlinx.coroutines.flow.Flow

interface GetCreditsMovieUseCase {
    suspend operator fun invoke(movieId: Int): Flow<Status<List<Actor>>>
}