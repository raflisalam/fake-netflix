package com.raflisalam.fakeneflix.common.helper

import com.raflisalam.fakeneflix.common.enums.MovieGenresList
import com.raflisalam.fakeneflix.common.enums.TvShowsGenresList

object Convert {

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

    fun toGenres(int: Int): String {
        return when (int) {
            28 -> MovieGenresList.ACTION.value.name
            12 -> MovieGenresList.ADVENTURE.value.name
            16 -> MovieGenresList.ANIMATION.value.name
            35 -> MovieGenresList.COMEDY.value.name
            80 -> MovieGenresList.CRIME.value.name
            99 -> MovieGenresList.DOCUMENTARY.value.name
            18 -> MovieGenresList.DRAMA.value.name
            10751 -> MovieGenresList.FAMILY.value.name
            14 -> MovieGenresList.FANTASY.value.name
            36 -> MovieGenresList.HISTORY.value.name
            27 -> MovieGenresList.HORROR.value.name
            10402 -> MovieGenresList.MUSIC.value.name
            9648 -> MovieGenresList.MYSTERY.value.name
            10749 -> MovieGenresList.ROMANCE.value.name
            878 -> MovieGenresList.SCIENCE_FICTION.value.name
            10770 -> MovieGenresList.TV_MOVIE.value.name
            53 -> MovieGenresList.THRILLER.value.name
            10752 -> MovieGenresList.WAR.value.name
            37 -> MovieGenresList.WESTERN.value.name
            10759 -> TvShowsGenresList.ACTION_ADVENTURE.value.name
            10762 -> TvShowsGenresList.KIDS.value.name
            10763 -> TvShowsGenresList.NEWS.value.name
            10764 -> TvShowsGenresList.REALITY.value.name
            10765 -> TvShowsGenresList.SCI_FI_FANTASY.value.name
            10766 -> TvShowsGenresList.SOAP.value.name
            10767 -> TvShowsGenresList.TALK.value.name
            10768 -> TvShowsGenresList.WAR_POLITICS.value.name
            else -> {
                ""
            }
        }
    }

}