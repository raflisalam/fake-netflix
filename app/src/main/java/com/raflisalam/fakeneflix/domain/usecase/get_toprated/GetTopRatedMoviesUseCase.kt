package com.raflisalam.fakeneflix.domain.usecase.get_toprated

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow


interface GetTopRatedMoviesUseCase {
    suspend operator fun invoke(page: Int): Flow<Status<List<Movies>>>
}