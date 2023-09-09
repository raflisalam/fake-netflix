package com.raflisalam.fakeneflix.common.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object TimeUtils {
    fun formatRuntimeToHoursMinutes(totalTimeMovies: Int): String {
        val hours = totalTimeMovies / 60
        val minutes = totalTimeMovies % 60
        return String.format("%dh %dm", hours, minutes)
    }

    fun formatDate(inputDate: String): String {
        if (inputDate.isNullOrEmpty()) {
            return ""
        }
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            val date = inputFormat.parse(inputDate)
            val formattedDate = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault()).format(date)
            formattedDate
        } catch (e: ParseException) {
            "Tanggal tidak valid"
        }
    }

    fun formatDateToYears(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())

        val year = inputFormat.parse(inputDate)
        return outputFormat.format(year)
    }
}