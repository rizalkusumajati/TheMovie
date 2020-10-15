package com.riztech.themovie.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.DaggerHomeComponent
import com.riztech.themovie.util.coreComponent
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerHomeComponent.builder().build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.test()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}