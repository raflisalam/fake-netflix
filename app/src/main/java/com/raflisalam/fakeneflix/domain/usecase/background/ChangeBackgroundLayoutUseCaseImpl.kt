package com.raflisalam.fakeneflix.domain.usecase.background

import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import javax.inject.Inject

class ChangeBackgroundLayoutUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): ChangeBackgroundLayoutUseCase {

    override suspend fun getBackgroundUrlNowPlaying(position: Int): String {
        val listMovies = repository.getNowPlayingMovies(1)
        var backgroundUrl = ""
        listMovies.collect {
            if (it.data?.indices?.contains(position) == true) {
                backgroundUrl = "${Constant.path_image_base_url}${it.data[position].poster}"
            }
        }
        return backgroundUrl
    }

    override suspend fun getBackgroundUrlUpcoming(position: Int): String {
        val listMovies = repository.getUpcomingMovies(1)
        var backgroundUrl = ""
        listMovies.collect {
            if (it.data?.indices?.contains(position) == true) {
                backgroundUrl = "${Constant.path_image_base_url}${it.data[position].poster}"
            }
        }
        return backgroundUrl
    }
}