package com.riztech.themovie.domain.usecase

import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.util.Resource


interface MovieListUseCase {
    suspend fun getMovieByGenre(page: Int, genreId: Int): Resource<List<HomeMovie>>
}