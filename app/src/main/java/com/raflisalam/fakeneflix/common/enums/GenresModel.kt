package com.raflisalam.fakeneflix.common.enums

data class GenresModel(
    val id: Int,
    val name: String
)

enum class GenresList(val value: GenresModel) {
    ACTION(GenresModel(28, "Action")),
    ACTION_ADVENTURE(GenresModel(10759, "Action & Adventure")),
    ADVENTURE(GenresModel(12, "Adventure")),
    ANIMATION(GenresModel(16, "Animation")),
    COMEDY(GenresModel(35, "Comedy")),
    CRIME(GenresModel(80, "Crime")),
    DOCUMENTARY(GenresModel(99, "Documentary")),
    DRAMA(GenresModel(18, "Drama")),
    FAMILY(GenresModel(10751, "Family")),
    FANTASY(GenresModel(14, "Fantasy")),
    HISTORY(GenresModel(36, "History")),
    HORROR(GenresModel(27, "Horror")),
    KIDS(GenresModel(10762, "Kids")),
    MUSIC(GenresModel(10402, "Music")),
    MYSTERY(GenresModel(9648, "Mystery")),
    NEWS(GenresModel(10763, "News")),
    REALITY(GenresModel(10764, "Reality")),
    ROMANCE(GenresModel(10749, "Romance")),
    SCIENCE_FICTION(GenresModel(878, "Science Fiction")),
    SCI_FI_FANTASY(GenresModel(10765, "Sci-Fi & Fantasy")),
    SOAP(GenresModel(10766, "Soap")),
    TALK(GenresModel(10767, "Talk")),
    TV_MOVIE(GenresModel(10770, "TV Movie")),
    THRILLER(GenresModel(53, "Thriller")),
    WAR(GenresModel(10752, "War")),
    WAR_POLITICS(GenresModel(10768, "War & Politics")),
    WESTERN(GenresModel(37, "Western"))

}

