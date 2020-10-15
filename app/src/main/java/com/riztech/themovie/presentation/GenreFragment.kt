package com.riztech.themovie.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.DaggerGenreComponent
import com.riztech.themovie.util.coreComponent
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [GenreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenreFragment : Fragment() {
    @Inject
    lateinit var viewModel: GenreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        DaggerGenreComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

}