package com.riztech.themovie.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riztech.themovie.R
import com.riztech.themovie.domain.model.Genre
import kotlinx.android.synthetic.main.genre_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(private val listGenre: ArrayList<Genre>, private val listener: OnClickHomeItem) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    interface OnClickHomeItem{
        fun clickGenre(genreId: Int)
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(genre: Genre, listener: OnClickHomeItem){

            itemView.apply {
                tvGenre.setText(genre.name)
                val rnd = Random()
                val color: Int =
                    Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

                view.setBackgroundColor(color)
                setOnClickListener { listener.clickGenre(genreId = genre.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder = HomeViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.genre_item, parent, false))

    override fun getItemCount(): Int = listGenre.count()

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) = holder.bind(listGenre[position], listener)

    fun addGenre(genre: List<Genre>) {
        this.listGenre.apply {
            clear()
            addAll(genre)
        }

    }
}