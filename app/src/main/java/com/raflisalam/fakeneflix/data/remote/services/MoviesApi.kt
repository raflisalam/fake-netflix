package com.raflisalam.fakeneflix.data.remote.services

import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.data.remote.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular?api_key=${Constant.API_KEY}")
    suspend fun getPopularMovies(
        @Query("page") position: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing?api_key=${Constant.API_KEY}")
    suspend fun getNowPlayingMovies(
        @Query("page") position: Int
    ): Response<MoviesResponse>

    @GET("movie/upcoming?api_key=${Constant.API_KEY}")
    suspend fun getUpcomingMovies(
        @Query("page") position: Int
    ): Response<MoviesResponse>
}