package com.raflisalam.fakeneflix.domain.usecase.discover

import com.raflisalam.fakeneflix.common.enums.MediaType
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverMovieNetwork
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverTvShowNetwork
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult
import com.raflisalam.fakeneflix.domain.repository.DiscoverRepository
import javax.inject.Inject


class GetDiscoverUseCaseImpl @Inject constructor(
    private val repository: DiscoverRepository,
): GetDiscoverUseCase {

    override suspend fun getSearchByGenre(
        genresId: ArrayList<String>,
        mediaType: MediaType
    ): List<DiscoverResult> {
        return when (mediaType) {
            MediaType.MOVIE -> {
                val movies = repository.getMoviesByGenre(genresId)
                movies.map { response ->
                    response.toDiscoverResult(MediaType.MOVIE)
                }
            }
            MediaType.TV_SHOW -> {
                val tvShows = repository.getTVShowsByGenre(genresId)
                tvShows.map { response ->
                    response.toDiscoverResult(MediaType.TV_SHOW)
                }
            }
        }
    }

    private fun DiscoverMovieNetwork.toDiscoverResult(mediaType: MediaType): DiscoverResult {
        return DiscoverResult(
            id = this.id,
            title = this.title,
            overview = this.overview,
            poster_path = this.poster_path,
            release_date = this.release_date,
            first_air_date = null, // this should null because on discover movie don't have object 'first_air_date'
            genre_ids = this.genre_ids,
            media_type = mediaType,
            vote_average = this.vote_average
        )
    }

    private fun DiscoverTvShowNetwork.toDiscoverResult(mediaType: MediaType): DiscoverResult {
        return DiscoverResult(
            id = this.id,
            title = this.name, // this should be name because tv show endpoint use object name as a title
            overview = this.overview,
            poster_path = this.poster_path,
            release_date = null, // this should null because on discover tv shows don't have object 'release_date'
            first_air_date = this.first_air_date,
            genre_ids = this.genre_ids,
            media_type = mediaType,
            vote_average = this.vote_average
        )
    }
}