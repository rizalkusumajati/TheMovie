package com.riztech.themovie.data.mappers

import com.riztech.themovie.data.model.local.MovieData
import com.riztech.themovie.data.model.network.*
import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.util.convertCompanies
import com.riztech.themovie.util.convertCountry
import com.riztech.themovie.util.convertGenre
import com.riztech.themovie.util.convertLanguage
import javax.inject.Inject

class DataToDomain @Inject constructor() {

    fun toHomeMovie(movieList: MovieByGenre): List<HomeMovie> {
        var homeList = arrayListOf<HomeMovie>()
        movieList?.let {
            it.results?.let {
                for (i in 0..it.size - 1) {
                    val resultMovie = it.get(i)
                    homeList.add(
                        HomeMovie(
                            resultMovie.id,
                            resultMovie.poster_path,
                            resultMovie.title,
                            resultMovie.video
                        )
                    )
                }
            }
        }

        return homeList
    }

    fun toHomeMovie(movieList: List<MovieData.Movies>): List<HomeMovie> {
        var homeList = arrayListOf<HomeMovie>()
        movieList?.let {
            for (i in 0..it.size - 1) {
                val resultMovie = it.get(i)
                homeList.add(HomeMovie(resultMovie.id, resultMovie.posterPath, resultMovie.title, resultMovie.video))
            }
        }

        return homeList
    }

    fun toGenre(genreList: GenreResponse): List<Genre> {
        var genreListResult = arrayListOf<Genre>()
        genreList?.let {
            it.genres?.let {
                for (i in 0..it.size - 1) {
                    val genre = it.get(i)
                    genreListResult.add(Genre(genre.id, genre.name))
                }
            }
        }

        return genreListResult
    }

    fun toGenre(list: List<MovieData.Genre>): List<Genre> {
        var genreList = arrayListOf<Genre>()
        list?.let {
            for (i in 0..it.size - 1) {
                val genre = it.get(i)
                genreList.add(Genre(genre.id, genre.name))
            }
        }

        return genreList
    }

    fun toDetail(detail: MovieDetail): com.riztech.themovie.domain.model.MovieDetail {
        return com.riztech.themovie.domain.model.MovieDetail(
            detail.adult,
            detail.backdrop_path,
            detail.budget,
            convertGenre(detail.genres),
            detail.homepage,
            detail.id,
            detail.imdb_id,
            detail.original_language,
            detail.original_title,
            detail.overview,
            detail.popularity,
            detail.poster_path,
            convertCompanies(detail.production_companies),
            convertCountry(detail.production_countries),
            detail.release_date,
            detail.revenue,
            detail.runtime,
            convertLanguage(detail.spoken_languages),
            detail.status,
            detail.tagline,
            detail.title,
            detail.video,
            detail.vote_average,
            detail.vote_count
        )
    }

    fun toDetail(detail: MovieData.Detail): com.riztech.themovie.domain.model.MovieDetail{
        return com.riztech.themovie.domain.model.MovieDetail(
            detail.adult,
            detail.backdrop_path,
            detail.budget,
            detail.genres,
            detail.homepage,
            detail.id,
            detail.imdb_id,
            detail.original_language,
            detail.original_title,
            detail.overview,
            detail.popularity,
            detail.poster_path,
            detail.production_companies,
            detail.production_countries,
            detail.release_date,
            detail.revenue,
            detail.runtime,
            detail.spoken_languages,
            detail.status,
            detail.tagline,
            detail.title,
            detail.video,
            detail.vote_average,
            detail.vote_count
        )
    }

    fun toTrailer(video: Video): MovieTrailer?{
        var movieTrailer: MovieTrailer? = null
        video.results?.let {
            for (i in 0..it.size-1){
                val trailer = it.get(i)
                if (trailer.site.equals("youtube", true)) {
                    movieTrailer =  MovieTrailer(
                        video.id,
                        trailer.key
                    )
                }
            }

        }

        return movieTrailer
    }

    fun toTrailer(video: MovieData.Trailer): MovieTrailer?{
        var movieTrailer: MovieTrailer? = null
        video?.let {
                val trailer = it
                if (trailer.site.equals("youtube", true)) {
                    movieTrailer =  MovieTrailer(
                        video.id,
                        trailer.key
                    )
                }
            }

        return movieTrailer
    }

    fun toReview(reviews: Reviews):List<Review>{
        var reviewList = arrayListOf<Review>()
        reviews?.let {
            it.results?.let {
                for (i in 0..it.size - 1) {
                    val review = it.get(i)
                    reviewList.add(Review(review.author, review.content, review.id, review.url))
                }
            }

        }

        return reviewList
    }

}