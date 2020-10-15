package com.riztech.themovie.domain.usecase

import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.util.Resource

interface HomeUseCase {
   suspend fun getGenre(): Resource<List<Genre>>
}