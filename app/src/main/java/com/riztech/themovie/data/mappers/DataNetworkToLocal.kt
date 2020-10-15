package com.riztech.themovie.data.mappers

import com.riztech.themovie.data.model.local.MovieData
import com.riztech.themovie.data.model.network.GenreResponse
import com.riztech.themovie.data.model.network.MovieByGenre
import com.riztech.themovie.data.model.network.MovieDetail
import com.riztech.themovie.data.model.network.Video
import com.riztech.themovie.util.convertCompanies
import com.riztech.themovie.util.convertCountry
import com.riztech.themovie.util.convertGenre
import com.riztech.themovie.util.convertLanguage
import javax.inject.Inject

class DataNetworkToLocal @Inject constructor() {
    fun networkToLocalGenre(genre: GenreResponse): List<MovieData.Genre>{
        var genreListResult = arrayListOf<MovieData.Genre>()
        genre?.let {
            it.genres?.let {
                for (i in 0..it.size - 1) {
                    val genre = it.get(i)
                    genreListResult.add(MovieData.Genre(genre.id, genre.name))
                }
            }
        }

        return genreListResult
    }

    fun networkToLocalMovies(movieByGenre: MovieByGenre): List<MovieData.Movies>{
        var moviesListResult = arrayListOf<MovieData.Movies>()
        movieByGenre?.let {
            it.results?.let {
                for (i in 0..it.size - 1) {
                    val movies = it.get(i)
                    moviesListResult.add(MovieData.Movies(movies.id, movies.adult, movies.backdrop_path, movies.genre_ids.toString(), movies.original_language,
                    movies.original_title, movies.overview, movies.popularity, movies.poster_path, movies.release_date, movies.title, movies.video,
                    movies.vote_average, movies.vote_count))
                }
            }
        }

        return moviesListResult
    }

    fun networkToLocalDetail(detail: MovieDetail): MovieData.Detail{
        return MovieData.Detail(
            detail.id,
            detail.adult,
            detail.backdrop_path,
            detail.budget,
            convertGenre(detail.genres),
            detail.homepage,
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

    fun networkToLocalTrailer(video: Video): MovieData.Trailer?{
        var movieTrailer: MovieData.Trailer? = null
        video.results?.let {
            for (i in 0..it.size-1){
                val trailer = it.get(i)
                if (trailer.site.equals("youtube", true)) {
                   movieTrailer =  MovieData.Trailer(
                        video.id,
                        trailer.key,
                        trailer.name,
                        trailer.site,
                        trailer.size,
                        trailer.type
                    )
                }
            }

        }

        return movieTrailer
    }
}