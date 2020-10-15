package com.riztech.themovie.data.model.network

data class GenreResponse(
    val genres: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String
)