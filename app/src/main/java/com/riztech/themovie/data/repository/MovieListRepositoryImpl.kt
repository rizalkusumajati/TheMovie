package com.riztech.themovie.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.riztech.themovie.data.api.MovieApi
import com.riztech.themovie.data.db.MoviesDao
import com.riztech.themovie.data.mappers.DataNetworkToLocal
import com.riztech.themovie.data.mappers.DataToDomain
import com.riztech.themovie.data.model.network.ErrorResponse
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.domain.repository.MovieListRepository
import com.riztech.themovie.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao,
    private val mapperToDomain: DataToDomain,
    private val mapper: DataNetworkToLocal
) : MovieListRepository {

    override suspend fun getMovieByGenreNetwork(page: Int, genre: Int): Resource<List<HomeMovie>> {
        return withContext(Dispatchers.IO) {
            try {
                movieApi.getMovieByGenres(page = page, genreId = genre)?.let {
                    moviesDao.insertMovies(mapper.networkToLocalMovies(it))
                    Resource.success(mapperToDomain.toHomeMovie(it))
                }
            } catch (error: IOException) {
                Resource.error(null, error.message?: "Error")
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

    override suspend fun getMovieByGenreLocal(page: Int, genre: Int): Resource<List<HomeMovie>> {
        return withContext(Dispatchers.IO){
            val list = moviesDao.select();
            if (list != null && list.size > 0){
                Log.d("TAG", "SUCCESS LOCAL")
                Resource.success(mapperToDomain.toHomeMovie(list))
            }else{
                getMovieByGenreNetwork(page, genre)
            }
        }
    }
}