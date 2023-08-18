package com.raflisalam.fakeneflix.data.remote.datasource

private const val STARTING_PAGE_INDEX = 1

/*
class MoviesPagingSource(private val apiServices: MovieApi): PagingSource<Int, ListMovie>()  {
    override fun getRefreshKey(state: PagingState<Int, ListMovie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiServices.getPopularMovies(position)
            val movies = response.body().results
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (movies.isEmpty()) null else position +1
            )
        } catch (e: IOException) {
           LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}*/
