package com.raflisalam.fakeneflix.data.remote.services

import android.graphics.Movie
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.data.remote.model.MovieDetailsDto
import com.raflisalam.fakeneflix.data.remote.model.MoviesResponse
import com.raflisalam.fakeneflix.data.remote.model.credits.MovieCredits
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
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

    @GET("movie/top_rated?api_key=${Constant.API_KEY}")
    suspend fun getTopRatedMovies(
        @Query("page") position: Int
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}?api_key=${Constant.API_KEY}&append_to_response=videos")
    suspend fun getDetailsMovieById(
        @Path("movie_id") moviesId: Int
    ): MovieDetailsDto

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovieById(
        @Path("movie_id") moviesId: Int
    ): Response<MovieCredits>

    @GET("search/movie")
    suspend fun getMoviesByName(
        @Query("query") moviesName: String
    ): Response<MoviesResponse>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timePeriod: String
    ): Response<MoviesResponse>

}