package com.riztech.themovie.domain.repository

import com.riztech.themovie.domain.model.MovieDetail
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.util.Resource

interface DetailRepository {
    suspend fun getDetailMovieNetwork(movieId: Int): Resource<MovieDetail>
    suspend fun getDetailMovieLocal(movieId: Int): Resource<MovieDetail>

    suspend fun getMovieTrailerNetwork(movieId: Int): Resource<MovieTrailer?>
    suspend fun getMovieTrailerLocal(movieId: Int): Resource<MovieTrailer?>

    suspend fun getReview(movieId: Int, page: Int): Resource<List<Review>>
}