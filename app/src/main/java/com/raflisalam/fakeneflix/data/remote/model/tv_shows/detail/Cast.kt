package com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail

data class Cast(
    val adult: Boolean,
    val id: Int,
    val character: String?,
    val credit_id: String,
    val gender: Int,
    val known_for_department: String,
    val name: String?,
    val order: Int,
    val original_name: String?,
    val popularity: Double,
    val profile_path: String?
)