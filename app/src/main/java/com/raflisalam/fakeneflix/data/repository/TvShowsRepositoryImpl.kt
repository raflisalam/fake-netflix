package com.raflisalam.fakeneflix.data.repository

import android.util.Log
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.getResponseTvShowsToModel
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.TvShowsDetailsDto
import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.domain.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class TvShowsRepositoryImpl @Inject constructor(
    private val apiServices: MoviesApi
): TvShowsRepository {

    override suspend fun getPopularTvShows(page: Int): Flow<Status<List<TvShows>>> = flow {
        try {
            emit(Status.Loading())
            val response = apiServices.getPopularTvShows(page)
            if (response.isSuccessful) {
                val tvShows = response.body()
                val listTvShows = getResponseTvShowsToModel(tvShows)
                emit(Status.Success(listTvShows))
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

    override suspend fun getDetailTvShowsById(seriesId: Int): TvShowsDetailsDto {
        val data = apiServices.getDetailTvShowsById(seriesId)
        Log.d("LOG_REPO", data.toString())
        return apiServices.getDetailTvShowsById(seriesId)
    }
}