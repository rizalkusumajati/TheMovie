package com.riztech.themovie.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.riztech.themovie.data.api.MovieApi
import com.riztech.themovie.data.db.GenreDao
import com.riztech.themovie.data.mappers.DataNetworkToLocal
import com.riztech.themovie.data.mappers.DataToDomain
import com.riztech.themovie.data.model.network.ErrorResponse
import com.riztech.themovie.domain.model.Genre
import com.riztech.themovie.domain.repository.HomeRepository
import com.riztech.themovie.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val movieApi: MovieApi, private val genreDao: GenreDao, private val mapperToDomain: DataToDomain, private val mapper: DataNetworkToLocal): HomeRepository {
    override suspend fun getGenreNetwork(): Resource<List<Genre>> {
        return withContext(Dispatchers.IO){
            try {
                movieApi.getMovieGenres()?.let {
                    genreDao.insertGenre(mapper.networkToLocalGenre(it))
                    Resource.success(mapperToDomain.toGenre(it))

                }
            } catch (error: IOException) {
                Resource.error(null, error.message?:"Error ")
            } catch (error: HttpException) {
                val body = error.response()!!.errorBody()
                val gson = Gson()
                val adapter: TypeAdapter<ErrorResponse> = gson.getAdapter(ErrorResponse::class.java)
                try {
                    val errorParser: ErrorResponse = adapter.fromJson(body!!.string())
                    Resource.error(null, errorParser.status_message?: "Error")
                } catch (e: IOException) {
                    Resource.error(null, error.message()?: "Error")
                }
            }

        }
    }

    override suspend fun getGenreLocal(): Resource<List<Genre>> {
        return withContext(Dispatchers.IO){
            val list = genreDao.select();
            if (list != null && list.size > 0){
               Resource.success(mapperToDomain.toGenre(list))
            }else{
                getGenreNetwork()
            }

        }
    }
}