package com.riztech.themovie.domain.repository

import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.util.Resource

interface HomeRepository {

    suspend fun getGenreNetwork(): Resource<List<Genre>>
    suspend fun getGenreLocal(): Resource<List<Genre>>
}