package com.raflisalam.fakeneflix.common.enums

data class TvShowsGenresModel(
    val id: Int,
    val name: String
)

enum class TvShowsGenresList(val value: TvShowsGenresModel) {
    ACTION_ADVENTURE(TvShowsGenresModel(10759, "Action & Adventure")),
    ANIMATION(TvShowsGenresModel(16, "Animation")),
    COMEDY(TvShowsGenresModel(35, "Comedy")),
    CRIME(TvShowsGenresModel(80, "Crime")),
    DOCUMENTARY(TvShowsGenresModel(99, "Documentary")),
    DRAMA(TvShowsGenresModel(18, "Drama")),
    FAMILY(TvShowsGenresModel(10751, "Family")),
    KIDS(TvShowsGenresModel(10762, "Kids")),
    MYSTERY(TvShowsGenresModel(9648, "Mystery")),
    NEWS(TvShowsGenresModel(10763, "News")),
    REALITY(TvShowsGenresModel(10764, "Reality")),
    SCI_FI_FANTASY(TvShowsGenresModel(10765, "Sci-Fi & Fantasy")),
    SOAP(TvShowsGenresModel(10766, "Soap")),
    TALK(TvShowsGenresModel(10767, "Talk")),
    WAR_POLITICS(TvShowsGenresModel(10768, "War & Politics")),
    WESTERN(TvShowsGenresModel(37, "Western"))
}
