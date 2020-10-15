package com.riztech.themovie.domain.model

data class HomeMovie (
    val id: Int,
    val coverUrl: String?,
    val movieTitle: String?,
    val video: Boolean
)