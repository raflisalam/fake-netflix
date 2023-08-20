package com.raflisalam.fakeneflix.common.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object PositionPageFlow {

    private val currentPageFlow = MutableStateFlow(0)
    private val currentPageName = MutableStateFlow("")

    fun getCurrentPageFlow(): Flow<Int> = currentPageFlow
    fun getCurrentPageName(): Flow<String> = currentPageName

    fun onPageSelected(position: Int, pageName: String) {
        currentPageFlow.value = position
        currentPageName.value = pageName
    }
}