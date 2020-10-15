package com.riztech.themovie.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riztech.themovie.data.model.network.Genre
import com.riztech.themovie.data.model.network.SpokenLanguage

sealed class MovieData {

    @Entity(tableName = "genres")
    data class Genre(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
        @ColumnInfo(name = "name") val name: String
    ) : MovieData()

    @Entity(tableName = "movies")
    data class Movies(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
        @ColumnInfo(name = "adult") val adult: Boolean,
        @ColumnInfo(name = "backdrop_path") val backdropPath: String,
        @ColumnInfo(name = "genre_ids") val genreId: String,
        @ColumnInfo(name = "original_language") val originalLang: String,
        @ColumnInfo(name = "original_title") val originalTitle: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "popularity") val popularity: Double,
        @ColumnInfo(name = "poster_path") val posterPath: String,
        @ColumnInfo(name = "release_date") val releaseDate: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "video") val video: Boolean,
        @ColumnInfo(name = "vote_average") val voteAverage: Double,
        @ColumnInfo(name = "vote_count") val voteCount: Int
    ): MovieData()

    @Entity(tableName = "detail_movie")
    data class Detail(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
        @ColumnInfo(name = "adult")val adult: Boolean,
        @ColumnInfo(name = "backdrop_path")val backdrop_path: String,
        @ColumnInfo(name = "budget")val budget: Int,
        @ColumnInfo(name = "genres") val genres: String,
        @ColumnInfo(name = "homepage")val homepage: String,
        @ColumnInfo(name = "imdb_id")val imdb_id: String,
        @ColumnInfo(name = "original_language")val original_language: String,
        @ColumnInfo(name = "original_title")val original_title: String,
        @ColumnInfo(name = "overview")val overview: String,
        @ColumnInfo(name = "popularity")val popularity: Double,
        @ColumnInfo(name = "poster_path")val poster_path: String,
        @ColumnInfo(name = "production_companies")val production_companies: String,
        @ColumnInfo(name = "production_countries")val production_countries: String,
        @ColumnInfo(name = "release_date")val release_date: String,
        @ColumnInfo(name = "revenue")val revenue: Int,
        @ColumnInfo(name = "runtime")val runtime: Int,
        @ColumnInfo(name = "spoken_languages")val spoken_languages: String,
        @ColumnInfo(name = "status") val status: String,
        @ColumnInfo(name = "tagline")val tagline: String,
        @ColumnInfo(name = "title")val title: String,
        @ColumnInfo(name = "video")val video: Boolean,
        @ColumnInfo(name = "vote_average")val vote_average: Double,
        @ColumnInfo(name = "vote_count")val vote_count: Int
    ): MovieData()

    @Entity(tableName = "video_trailer")
    data class Trailer(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
        @ColumnInfo(name = "key")val key: String,
        @ColumnInfo(name = "name")val name: String,
        @ColumnInfo(name = "site")val site: String,
        @ColumnInfo(name = "size")val size: Int,
        @ColumnInfo(name = "type")val type: String
    )

}