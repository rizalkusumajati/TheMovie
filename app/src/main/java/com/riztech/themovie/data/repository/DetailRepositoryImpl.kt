package com.riztech.themovie.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.riztech.themovie.data.api.MovieApi
import com.riztech.themovie.data.db.MoviesDetailDao
import com.riztech.themovie.data.db.TrailerDao
import com.riztech.themovie.data.mappers.DataNetworkToLocal
import com.riztech.themovie.data.mappers.DataToDomain
import com.riztech.themovie.data.model.local.MovieData
import com.riztech.themovie.data.model.network.ErrorResponse
import com.riztech.themovie.data.model.network.Video
import com.riztech.themovie.domain.model.MovieDetail
import com.riztech.themovie.domain.model.MovieTrailer
import com.riztech.themovie.domain.model.Review
import com.riztech.themovie.domain.repository.DetailRepository
import com.riztech.themovie.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val movieApi: MovieApi, private val moviesDetailDao: MoviesDetailDao, private val trailerDao: TrailerDao, private val mapperToDomain: DataToDomain, private val mapper: DataNetworkToLocal) : DetailRepository {
    override suspend fun getDetailMovieNetwork(movieId: Int): Resource<MovieDetail> {
        return withContext(Dispatchers.IO){
            try {
                movieApi.getMovieDetail(movieId = movieId)?.let {
                    moviesDetailDao.insertMoviesDetail(mapper.networkToLocalDetail(it))
                    Resource.success(mapperToDomain.toDetail(it))

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

    override suspend fun getDetailMovieLocal(movieId: Int): Resource<MovieDetail> {
        return withContext(Dispatchers.IO){
            val list = moviesDetailDao.select(movieId);
            if (list != null && list.id > 0){
                Resource.success(mapperToDomain.toDetail(list))
            }else{
                getDetailMovieNetwork(movieId)
            }

        }
    }

    override suspend fun getMovieTrailerNetwork(movieId: Int): Resource<MovieTrailer?> {
        return withContext(Dispatchers.IO){
            try {
                movieApi.getTrailerMovie(movieId = movieId)?.let {
                    val trailer = mapper.networkToLocalTrailer(it)
                    trailer?.let {
                        trailerDao.insertMoviesTrailer(trailer)
                    }
                    Resource.success(mapperToDomain.toTrailer(it))

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

    override suspend fun getMovieTrailerLocal(movieId: Int): Resource<MovieTrailer?> {
        return withContext(Dispatchers.IO){
            val list = trailerDao.select(movieId);
            if (list != null && list.id > 0){
                Resource.success(mapperToDomain.toTrailer(list))
            }else{
                getMovieTrailerNetwork(movieId)
            }

        }
    }

    override suspend fun getReview(movieId: Int, page: Int): Resource<List<Review>> {
        return withContext(Dispatchers.IO){
            try {
                movieApi.getReviewsMovie(movieId = movieId, page = page)?.let {
                    Resource.success(mapperToDomain.toReview(it))
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
}