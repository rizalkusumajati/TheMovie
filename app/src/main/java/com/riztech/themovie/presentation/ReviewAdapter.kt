package com.riztech.themovie.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riztech.themovie.R
import com.riztech.themovie.domain.model.Review
import kotlinx.android.synthetic.main.review_list.view.*

class ReviewAdapter (private val listMovies: ArrayList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewListViewHolder>() {

    fun addDataToExisting(listUpdate: List<Review>){
        listMovies.addAll(listUpdate)
        notifyDataSetChanged()
    }


    class ReviewListViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(review: Review){
            itemView.apply {
                tvAuthor.setText(review.author)
                tvReview.setText(review.content)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListViewHolder = ReviewListViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.review_list, parent, false))

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ReviewListViewHolder, position: Int) = holder.bind(listMovies[position])
}