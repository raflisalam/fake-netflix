package com.raflisalam.fakeneflix.domain.repository

import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.data.remote.model.actors.ActorsDetailsDto
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits
import kotlinx.coroutines.flow.Flow

interface ActorsRepository {

    suspend fun getPopularActors(page: Int): Flow<Status<List<Actors>>>
    suspend fun getDetailActorsById(personId: Int): ActorsDetailsDto
    suspend fun getMovieCreditsActor(personId: Int): Flow<Status<List<ActorsMovieCredits>>>
}