package com.raflisalam.fakeneflix.data.remote.model.tv_shows

data class TvShowsResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)