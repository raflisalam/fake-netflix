package com.raflisalam.fakeneflix.common.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ActorsIdStateFlow {

    private val currentIdActors = MutableStateFlow(0)
    fun getCurrentIdActors(): StateFlow<Int> = currentIdActors

    fun onActorsSelected(actorsId: Int) {
        currentIdActors.value = actorsId
    }
}