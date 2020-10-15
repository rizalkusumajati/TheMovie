package com.riztech.themovie.domain.model

import com.riztech.themovie.data.model.network.Genre
import com.riztech.themovie.data.model.network.SpokenLanguage

data class MovieDetail (
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
    val genres: String,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: String,
    val production_countries: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: String,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)