package com.raflisalam.fakeneflix.domain.usecase.background

interface ChangeBackgroundLayoutUseCase {

    suspend fun getBackgroundUrlNowPlaying(position: Int): String
    suspend fun getBackgroundUrlUpcoming(position: Int): String
}