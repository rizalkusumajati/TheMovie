package com.riztech.themovie.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riztech.themovie.R
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.util.getProgressDrawable
import com.riztech.themovie.util.loadImage
import kotlinx.android.synthetic.main.movie_home_list.view.*

class MovieListAdapter(private val listMovies: ArrayList<HomeMovie>, private val listener: OnClickMovieListItem) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    fun addDataToExisting(listUpdate: List<HomeMovie>){
        listMovies.addAll(listUpdate)
        notifyDataSetChanged()
    }

    fun clearData(){
        listMovies.clear()
        notifyDataSetChanged()
    }

    interface OnClickMovieListItem{
        fun clickMovie(homeMovie: HomeMovie)
    }

    class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(homeMovie: HomeMovie, listener: OnClickMovieListItem){
            itemView.apply {
                ivPoster.loadImage("http://image.tmdb.org/t/p/w185/${homeMovie.coverUrl}", getProgressDrawable(context), context)
                tvTitle.setText(homeMovie.movieTitle)

                setOnClickListener {
                    listener.clickMovie(homeMovie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder = MovieListViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.movie_home_list, parent, false))

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) = holder.bind(listMovies[position], listener)
}