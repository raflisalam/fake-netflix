package com.raflisalam.fakeneflix.domain.usecase.get_detail_movies.get_recommendations

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import kotlinx.coroutines.flow.Flow

interface GetRecommendationsMoviesUseCase {
    suspend operator fun invoke(movieId: Int, page: Int): Flow<Status<List<Movies>>>
}