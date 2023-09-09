package com.raflisalam.fakeneflix.domain.usecase.tv_shows.get_detail

import android.util.Log
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.toTvShowsDetail
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import com.raflisalam.fakeneflix.domain.repository.TvShowsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GetDetailTvShowsUseCase @Inject constructor(
    private val repository: TvShowsRepository
) {
    operator fun invoke(seriesId: Int): Flow<Status<TvShowsDetail>> = flow {
        try {
            emit(Status.Loading())
            val series = repository.getDetailTvShowsById(seriesId).toTvShowsDetail()
            emit(Status.Success(series))
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }

}