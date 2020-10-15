package com.riztech.themovie.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.domain.usecase.HomeUseCaseImpl
import com.riztech.themovie.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeUseCaseImpl: HomeUseCaseImpl) :
    ViewModel() {

    private val genre = MutableLiveData<Resource<List<Genre>>>()
    val _genre: LiveData<Resource<List<Genre>>>
        get() = genre

    fun getGenre() {
        genre.postValue(Resource.loading(null))
        viewModelScope.launch {
            val list = homeUseCaseImpl.getGenre()
            list.data?.let {
                for (i in 0..list.data.size - 1) {
                    println(list.data.get(i))
                }
            }

            genre.postValue(list)


        }
    }
}


