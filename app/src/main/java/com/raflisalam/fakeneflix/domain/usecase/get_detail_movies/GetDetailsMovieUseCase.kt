package com.raflisalam.fakeneflix.domain.usecase.get_detail_movies

import android.util.Log
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.movies.toMovieDetails
import com.raflisalam.fakeneflix.domain.model.movies.MovieDetails
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException


class GetDetailsMovieUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(movieId: Int): Flow<Status<MovieDetails>> = flow {
        try {
            emit(Status.Loading())
            val movies = repository.getDetailsMovieById(movieId).toMovieDetails()
            emit(Status.Success(movies))
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }
}