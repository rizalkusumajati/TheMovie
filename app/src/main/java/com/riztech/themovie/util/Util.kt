package com.riztech.themovie.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.CoreComponentProvider
import com.riztech.themovie.data.model.network.ProductionCompanies

fun Activity.coreComponent() =
    (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
        ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")

fun Fragment.coreComponent() = requireActivity().coreComponent()

enum class Status{
    LOADING,
    SUCCESS,
    ERROR
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}

fun getProgressDrawable(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 20f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable, context: Context){
    val options = RequestOptions()
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}


fun convertGenre(list: List<com.riztech.themovie.data.model.network.Genre>): String {
    var listGenre = ""
    list?.let {
        for (i in 0..it.size - 1) {
            listGenre += it.get(i).name
        }
    }
    return listGenre
}

fun convertLanguage(list: List<com.riztech.themovie.data.model.network.SpokenLanguage>): String {
    var listLang = ""
    list?.let {
        for (i in 0..it.size - 1) {
            listLang += it.get(i).name
        }
    }
    return listLang
}

fun convertCompanies(list: List<ProductionCompanies>): String {
    var listCompanies = ""
    list?.let {
        for (i in 0..it.size - 1) {
            listCompanies += it.get(i).name
        }
    }
    return listCompanies
}

fun convertCountry(list: List<com.riztech.themovie.data.model.network.ProductionCountries>): String {
    var listCountries = ""
    list?.let {
        for (i in 0..it.size - 1) {
            listCountries += it.get(i).name
        }
    }
    return listCountries
}