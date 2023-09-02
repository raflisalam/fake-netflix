package com.raflisalam.fakeneflix.common.enums

data class MovieGenresModel(
    val id: Int,
    val name: String
)

enum class MovieGenresList(val value: MovieGenresModel) {
    ACTION(MovieGenresModel(28, "Action")),
    ADVENTURE(MovieGenresModel(12, "Adventure")),
    ANIMATION(MovieGenresModel(16, "Animation")),
    COMEDY(MovieGenresModel(35, "Comedy")),
    CRIME(MovieGenresModel(80, "Crime")),
    DOCUMENTARY(MovieGenresModel(99, "Documentary")),
    DRAMA(MovieGenresModel(18, "Drama")),
    FAMILY(MovieGenresModel(10751, "Family")),
    FANTASY(MovieGenresModel(14, "Fantasy")),
    HISTORY(MovieGenresModel(36, "History")),
    HORROR(MovieGenresModel(27, "Horror")),
    MUSIC(MovieGenresModel(10402, "Music")),
    MYSTERY(MovieGenresModel(9648, "Mystery")),
    ROMANCE(MovieGenresModel(10749, "Romance")),
    SCIENCE_FICTION(MovieGenresModel(878, "Science Fiction")),
    TV_MOVIE(MovieGenresModel(10770, "TV Movie")),
    THRILLER(MovieGenresModel(53, "Thriller")),
    WAR(MovieGenresModel(10752, "War")),
    WESTERN(MovieGenresModel(37, "Western"))

}

