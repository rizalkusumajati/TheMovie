package com.riztech.themovie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riztech.themovie.domain.model.MovieDetail
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.domain.usecase.DetailUseCaseImpl
import com.riztech.themovie.util.Resource
import com.riztech.themovie.util.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val detailUseCaseImpl: DetailUseCaseImpl) : ViewModel() {

    private val detail = MutableLiveData<Resource<MovieDetail>>()
    val _detail: LiveData<Resource<MovieDetail>>
    get() = detail

    private val trailer = MutableLiveData<Resource<MovieTrailer?>>()
    val _trailer : LiveData<Resource<MovieTrailer?>>
    get() = trailer

    private val reviews = MutableLiveData<Resource<List<Review>>>()
    val _reviews: LiveData<Resource<List<Review>>>
    get() = reviews

    private var page = 1;

    fun getDetail(movieId: Int){
        detail.postValue(Resource.loading(null))
        viewModelScope.launch {
            val list = detailUseCaseImpl.getDetailMovie(movieId)
            detail.postValue(list)
        }
    }

    fun getTrailer(movieId: Int){
        trailer.postValue(Resource.loading(null))
        viewModelScope.launch {
            val trailerVal = detailUseCaseImpl.getTrailer(movieId)
            trailer.postValue(trailerVal)
        }
    }

    fun getReviews(movieId: Int){
        reviews.postValue(Resource.loading(null))
        viewModelScope.launch {
            val reviewVal = detailUseCaseImpl.getReviews(movieId, page)
            reviews.postValue(reviewVal)
            if (reviewVal.status == Status.SUCCESS){
                page++
            }
        }
    }
}