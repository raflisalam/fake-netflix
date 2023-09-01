package com.raflisalam.fakeneflix.domain.usecase.get_detail_actors

import android.util.Log
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.actors.toActorsDetail
import com.raflisalam.fakeneflix.data.remote.model.movies.toMovieDetails
import com.raflisalam.fakeneflix.domain.model.actors.ActorsDetail
import com.raflisalam.fakeneflix.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetDetailActorsUseCase @Inject constructor(
    private val repository: ActorsRepository
) {
    operator fun invoke(personId: Int): Flow<Status<ActorsDetail>> = flow {
        try {
            emit(Status.Loading())
            val actors = repository.getDetailActorsById(personId).toActorsDetail()
            emit(Status.Success(actors))
        } catch (e: HttpException) {
            emit(Status.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Status.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Status.Error("An unexpected error occurred"))
        }
    }
}