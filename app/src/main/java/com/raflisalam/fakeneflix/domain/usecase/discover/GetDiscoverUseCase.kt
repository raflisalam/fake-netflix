package com.raflisalam.fakeneflix.domain.usecase.discover

import com.raflisalam.fakeneflix.common.enums.MediaType
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult

interface GetDiscoverUseCase {
    suspend fun getSearchByGenre(genresId: ArrayList<String>, mediaType: MediaType): List<DiscoverResult>
}
