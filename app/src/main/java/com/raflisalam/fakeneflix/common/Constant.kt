package com.raflisalam.fakeneflix.common

import com.raflisalam.fakeneflix.BuildConfig

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = BuildConfig.MOVIE_API_KEY
    const val AUTH_KEY = BuildConfig.AUTH_KEY

    const val path_image_base_url = "https://image.tmdb.org/t/p/w500"
    const val video_base_url = "https://www.youtube.com/embed/"
}