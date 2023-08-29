package com.raflisalam.fakeneflix.common.utils

import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity

interface OnItemMoviesSwipeListener {
    fun onMovieSwiped(movie: WatchlistMoviesEntity)
}