package com.raflisalam.fakeneflix.common.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SeriesIdStateFlow {

    private val currentIdSeries = MutableStateFlow(0)

    fun getCurrentIdSeries(): StateFlow<Int> = currentIdSeries

    fun onSeriesSelected(seriesId: Int) {
        currentIdSeries.value = seriesId
    }
}