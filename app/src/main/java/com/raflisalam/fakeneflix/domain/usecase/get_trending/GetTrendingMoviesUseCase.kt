package com.raflisalam.fakeneflix.domain.usecase.get_trending

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow

interface GetTrendingMoviesUseCase {

    suspend operator fun invoke(timePeriod: String): Flow<Status<List<Movies>>>
}