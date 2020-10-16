package com.riztech.themovie.data.model.network

data class Reviews(
    val id: Int,
    val page: Int,
    val results: List<ResultReviews>,
    val total_pages: Int,
    val total_results: Int
)

data class ResultReviews(
    val author: String?,
    val content: String?,
    val id: String?,
    val url: String?
)