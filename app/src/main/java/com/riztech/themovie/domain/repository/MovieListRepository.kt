package com.riztech.themovie.domain.repository

import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.util.Resource

interface MovieListRepository {
    suspend fun getMovieByGenreNetwork(page: Int, genre: Int): Resource<List<HomeMovie>>
    suspend fun getMovieByGenreLocal(page: Int, genre: Int): Resource<List<HomeMovie>>
}