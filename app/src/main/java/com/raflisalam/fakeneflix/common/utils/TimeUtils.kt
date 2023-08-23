package com.raflisalam.fakeneflix.common.utils

object TimeUtils {
    fun formatRuntimeToHoursMinutes(totalTimeMovies: Int): String {
        val hours = totalTimeMovies / 60
        val minutes = totalTimeMovies % 60
        return String.format("%dh %dm", hours, minutes)
    }
}