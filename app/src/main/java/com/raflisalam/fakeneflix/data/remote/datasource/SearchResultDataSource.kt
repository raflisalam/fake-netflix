package com.raflisalam.fakeneflix.data.remote.datasource

import com.raflisalam.fakeneflix.data.remote.model.search.SearchResponse
import com.raflisalam.fakeneflix.data.remote.services.MoviesApi
import retrofit2.Response
import javax.inject.Inject

class SearchResultDataSource @Inject constructor(
    private val apiServices: MoviesApi
) {
    suspend fun fetchSearchResults(query: String): Response<SearchResponse> {
        return apiServices.searchByName(query)
    }
}