package com.riztech.themovie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.domain.usecase.MovieListUseCaseImpl
import com.riztech.themovie.util.Resource
import com.riztech.themovie.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val movieListUseCaseImpl: MovieListUseCaseImpl) :
    ViewModel() {
    private val movies = MutableLiveData<Resource<List<HomeMovie>>>()
    val _movies: LiveData<Resource<List<HomeMovie>>>
        get() = movies

    var _page = 1;

    fun getMovieByGenre(genreId: Int){
        movies.postValue(Resource.loading(null))
        viewModelScope.launch {
            val list = movieListUseCaseImpl.getMovieByGenre(_page, genreId)

            if (list.status == Status.SUCCESS){
                _page++
            }
            list.data?.let {
                for (i in 0..list.data.size - 1) {
                    println(list.data.get(i))
                }
            }

            movies.postValue(list)


        }
    }
}