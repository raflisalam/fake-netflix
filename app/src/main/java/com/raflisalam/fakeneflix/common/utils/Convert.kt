package com.raflisalam.fakeneflix.common.utils

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
}