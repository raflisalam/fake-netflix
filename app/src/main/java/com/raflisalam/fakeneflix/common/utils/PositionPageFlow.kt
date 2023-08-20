package com.raflisalam.fakeneflix.common.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object PositionPageFlow {

    private val currentPageName = MutableStateFlow("")
    private val currentPageFlow = MutableStateFlow(0)

    fun getCurrentPageName(): Flow<String> = currentPageName
    fun getCurrentPageFlow(): Flow<Int> = currentPageFlow

    fun onPageSelected(pageName: String, position: Int, ) {
        currentPageName.value = pageName
        currentPageFlow.value = position
    }
}