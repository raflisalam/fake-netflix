package com.raflisalam.fakeneflix.data.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.getResponseActorsToModel
import com.raflisalam.fakeneflix.common.getResponseMovieCreditsToModel
import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsDetailsDto
import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits
import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val apiServices: MoviesApi
): ActorsRepository {

    override suspend fun getPopularActors(page: Int): Flow<Status<List<Actors>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getPopularActors(page)
            if (response.isSuccessful) {
                val actorsResponse = response.body()
                val actorsList = getResponseActorsToModel(actorsResponse)
                emit(Status.Success(actorsList))
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

    override suspend fun getDetailActorsById(personId: Int): ActorsDetailsDto {
        return apiServices.getDetailActorsById(personId)
    }

    override suspend fun getMovieCreditsActor(personId: Int): Flow<Status<List<ActorsMovieCredits>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getMovieCreditsActor(personId)
            if (response.isSuccessful) {
                val credits = response.body()
                val listMovies = getResponseMovieCreditsToModel(credits)
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

}