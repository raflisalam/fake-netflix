package com.raflisalam.fakeneflix.data.remote.services

import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsDetailsDto
import com.raflisalam.fakeneflix.data.remote.model.movies.MovieDetailsDto
import com.raflisalam.fakeneflix.data.remote.model.movies.MoviesResponse
import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsResponse
import com.raflisalam.fakeneflix.data.remote.model.actors.MovieCredits
import com.raflisalam.fakeneflix.data.remote.model.credits.CreditsMovie
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverMovieResponse
import com.raflisalam.fakeneflix.data.remote.model.discover.DiscoverTvShowResponse
import com.raflisalam.fakeneflix.data.remote.model.search.SearchResponse
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.TvShowsResponse
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.TvShowsDetailsDto
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import retrofit2.Response
import retrofit2.http.GET
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

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovieById(
        @Path("movie_id") moviesId: Int
    ): Response<CreditsMovie>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationsMovies(
        @Path("movie_id") moviesId: Int,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun getMoviesByName(
        @Query("query") moviesName: String
    ): Response<MoviesResponse>


    @GET("movie/{movie_id}?api_key=${Constant.API_KEY}&append_to_response=videos")
    suspend fun getDetailsMovieById(
        @Path("movie_id") moviesId: Int
    ): MovieDetailsDto

    //actors endpoint services
    @GET("person/popular?api_key${Constant.API_KEY}")
    suspend fun getPopularActors(
        @Query("page") position: Int
    ): Response<ActorsResponse>

    @GET("person/{person_id}?api_key=${Constant.API_KEY}&append_to_response=movie_credits")
    suspend fun getDetailActorsById(
        @Path("person_id") personId: Int
    ): ActorsDetailsDto

    @GET("person/{person_id}/movie_credits")
    suspend fun getMovieCreditsActor(
        @Path("person_id") personId: Int
    ): Response<MovieCredits>

    //tv shows endpoint services
    @GET("tv/top_rated?api_key=${Constant.API_KEY}")
    suspend fun getPopularTvShows(
        @Query("page") position: Int
    ): Response<TvShowsResponse>

    @GET("tv/{series_id}?append_to_response=credits")
    suspend fun getDetailTvShowsById(
        @Path("series_id") seriesId: Int
    ): TvShowsDetailsDto

    @GET("tv/{series_id}/recommendations")
    suspend fun getRecommendationsTvShows(
        @Path("series_id") moviesId: Int,
        @Query("page") page: Int
    ): Response<TvShowsResponse>

    //search
    @GET("search/multi")
    suspend fun searchByName(
        @Query("query") query: String
    ): Response<SearchResponse>

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("with_genres") genresId: ArrayList<String>
    ): Response<DiscoverMovieResponse>

    @GET("discover/tv")
    suspend fun discoverTvShowsByGenre(
        @Query("with_genres") genresId: ArrayList<String>
    ): Response<DiscoverTvShowResponse>

    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(
        @Path("time_window") timePeriod: String
    ): Response<MoviesResponse>

}