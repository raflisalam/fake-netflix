package com.raflisalam.fakeneflix.domain.usecase.get_upcoming

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(page: Int): Flow<Status<List<Movies>>>
}