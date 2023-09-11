package com.raflisalam.fakeneflix.domain.usecase.discover

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.enums.MediaType
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverMovieNetwork
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverTvShowNetwork
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult
import com.raflisalam.fakeneflix.domain.repository.DiscoverRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


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
            poster_path = this.posterPath,
            release_date = this.releaseDate,
            first_air_date = null, // Tidak ada firstAirDate untuk film
            genre_ids = this.genreIds,
            media_type = mediaType,
            vote_average = this.voteAverage
        )
    }

    private fun DiscoverTvShowNetwork.toDiscoverResult(mediaType: MediaType): DiscoverResult {
        return DiscoverResult(
            id = this.id,
            title = this.name, // Menggunakan nama sebagai judul untuk acara TV
            overview = this.overview,
            poster_path = this.posterPath,
            release_date = null, // Tidak ada releaseDate untuk acara TV
            first_air_date = this.firstAirDate,
            genre_ids = this.genreIds,
            media_type = mediaType,
            vote_average = this.voteAverage
        )
    }
}