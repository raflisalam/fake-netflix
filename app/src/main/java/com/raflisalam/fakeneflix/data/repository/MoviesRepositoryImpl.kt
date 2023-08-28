package com.raflisalam.fakeneflix.data.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.getResponseCreditsCastToModel
import com.raflisalam.fakeneflix.common.getResponseMovieToModel
import com.raflisalam.fakeneflix.data.remote.model.MovieDetailsDto
import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import com.raflisalam.fakeneflix.domain.model.Actor
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val apiServices: MoviesApi
): MoviesRepository {

    override suspend fun getPopularMovies(page: Int): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getPopularMovies(page)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = getResponseMovieToModel(movieResponse)
                emit(Status.Success(movieList))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getNowPlayingMovies(page)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = getResponseMovieToModel(movieResponse)
                emit(Status.Success(movieList))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getUpcomingMovies(page)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = getResponseMovieToModel(movieResponse)
                emit(Status.Success(movieList))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getTopRatedMovies(page)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = getResponseMovieToModel(movieResponse)
                emit(Status.Success(movieList))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getDetailsMovieById(movieId: Int): MovieDetailsDto {
        return apiServices.getDetailsMovieById(movieId)
    }

    override suspend fun getCreditsActorById(movieId: Int): Flow<Status<List<Actor>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getCreditsMovieById(movieId)
            if (response.isSuccessful) {
                val credits = response.body()
                val listActor = getResponseCreditsCastToModel(credits)
                emit(Status.Success(listActor))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error("An unexpected error occurred ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred ${e.localizedMessage}"))
        }
    }

    override suspend fun getMoviesByName(moviesName: String): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getMoviesByName(moviesName)
            if (response.isSuccessful) {
                val movies = response.body()
                val listMovies = getResponseMovieToModel(movies)
                emit(Status.Success(listMovies))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error("An unexpected error occurred ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred ${e.localizedMessage}"))
        }
    }

    override suspend fun getTrendingMovies(timePeriod: String): Flow<Status<List<Movies>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getTrendingMovies(timePeriod)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = getResponseMovieToModel(movieResponse)
                emit(Status.Success(movieList))
            } else {
                emit(Status.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }    }
}