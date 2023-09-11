package com.raflisalam.fakeneflix.common.helper

import com.raflisalam.fakeneflix.common.enums.GenresList
import com.raflisalam.fakeneflix.common.exception.GenresNotFoundException

object Convert {

    fun roundDouble(double: Double): Double {
        return Math.round(double * 10.0) / 10.0
    }

    fun toGenders(int: Int): String {
        var gender = ""
        if (int == 1) {
            gender = "Female"
        } else if (int == 2) {
            gender = "Male"
        }
        return gender
    }

    fun toCareers(int: Int): String {
        var careers = ""
        if (int == 1) {
            careers = "Actress"
        } else if (int == 2) {
            careers = "Actors"
        }
        return careers
    }

    fun genresListIdToListString(listId: List<Int>): String {
        val rawGenreName = listId.mapNotNull { id ->
            GenresList.values().find { it.value.id == id }?.value?.name
        }
        return rawGenreName.joinToString(", ")
    }

    fun genresToInt(string: String): Int {
        return when(string) {
            "Action" -> GenresList.ACTION.value.id
            "Adventure" -> GenresList.ADVENTURE.value.id
            "Animation" -> GenresList.ANIMATION.value.id
            "Comedy" -> GenresList.COMEDY.value.id
            "Crime" -> GenresList.CRIME.value.id
            "Documentary" -> GenresList.DOCUMENTARY.value.id
            "Drama" -> GenresList.DRAMA.value.id
            "Family" -> GenresList.FAMILY.value.id
            "Fantasy" -> GenresList.FANTASY.value.id
            "History" -> GenresList.HISTORY.value.id
            "Horror" -> GenresList.HORROR.value.id
            "Music" -> GenresList.MUSIC.value.id
            "Mystery" -> GenresList.MYSTERY.value.id
            "Romance" -> GenresList.ROMANCE.value.id
            "Science Fiction" -> GenresList.SCIENCE_FICTION.value.id
            "TV Movie" -> GenresList.TV_MOVIE.value.id
            "Thriller" -> GenresList.THRILLER.value.id
            "War" -> GenresList.WAR.value.id
            "Western" -> GenresList.WESTERN.value.id
            "Action & Adventure" -> GenresList.ACTION_ADVENTURE.value.id
            "Kids" -> GenresList.KIDS.value.id
            "News" -> GenresList.NEWS.value.id
            "Reality" -> GenresList.REALITY.value.id
            "Sci-Fi & Fantasy" -> GenresList.SCI_FI_FANTASY.value.id
            "Soap" -> GenresList.SOAP.value.id
            "Talk" -> GenresList.TALK.value.id
            "War & Politics" -> GenresList.WAR_POLITICS.value.id
            else -> throw GenresNotFoundException("Genres Not Found")
        }
    }

    fun toGenres(int: Int): String {
        return when (int) {
            28 -> GenresList.ACTION.value.name
            12 -> GenresList.ADVENTURE.value.name
            16 -> GenresList.ANIMATION.value.name
            35 -> GenresList.COMEDY.value.name
            80 -> GenresList.CRIME.value.name
            99 -> GenresList.DOCUMENTARY.value.name
            18 -> GenresList.DRAMA.value.name
            10751 -> GenresList.FAMILY.value.name
            14 -> GenresList.FANTASY.value.name
            36 -> GenresList.HISTORY.value.name
            27 -> GenresList.HORROR.value.name
            10402 -> GenresList.MUSIC.value.name
            9648 -> GenresList.MYSTERY.value.name
            10749 -> GenresList.ROMANCE.value.name
            878 -> GenresList.SCIENCE_FICTION.value.name
            10770 -> GenresList.TV_MOVIE.value.name
            53 -> GenresList.THRILLER.value.name
            10752 -> GenresList.WAR.value.name
            37 -> GenresList.WESTERN.value.name
            10759 -> GenresList.ACTION_ADVENTURE.value.name
            10762 -> GenresList.KIDS.value.name
            10763 -> GenresList.NEWS.value.name
            10764 -> GenresList.REALITY.value.name
            10765 -> GenresList.SCI_FI_FANTASY.value.name
            10766 -> GenresList.SOAP.value.name
            10767 -> GenresList.TALK.value.name
            10768 -> GenresList.WAR_POLITICS.value.name
            else -> throw GenresNotFoundException("Id genres Not Found")
        }
    }

}