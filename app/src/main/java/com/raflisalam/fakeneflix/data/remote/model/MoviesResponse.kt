package com.raflisalam.fakeneflix.data.remote.model

import android.os.Parcelable


data class MoviesResponse(
    val results: List<Results>
)

data class Results(
    val id: Int?,
    val overview: String?,
    val release_date: String?,
    val backdrop_path: String?,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?
)
