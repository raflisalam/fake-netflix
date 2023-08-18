package com.raflisalam.fakeneflix.domain.usecase.get_nowplaying

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.domain.model.Movies
import kotlinx.coroutines.flow.Flow


interface GetNowPlayingMoviesUseCase {
    suspend operator fun invoke(page: Int): Flow<Status<List<Movies>>>
}