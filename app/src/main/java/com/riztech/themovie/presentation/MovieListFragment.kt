package com.riztech.themovie.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.DaggerMovieListComponent
import com.riztech.themovie.domain.model.HomeMovie
import com.riztech.themovie.util.EndlessOnScrollListener
import com.riztech.themovie.util.Status
import com.riztech.themovie.util.coreComponent
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieListFragment : Fragment(), MovieListAdapter.OnClickMovieListItem {
    @Inject
    lateinit var viewModel: MovieListViewModel
    lateinit var adapter: MovieListAdapter

    val genreBundle : MovieListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMovieListComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieByGenre(genreBundle.genre)

        rvMovieList.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieListAdapter(arrayListOf(), this)
        rvMovieList.addItemDecoration(
            DividerItemDecoration(
                rvMovieList.context,
                (rvMovieList.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvMovieList.adapter = adapter

        rvMovieList.addOnScrollListener(object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                viewModel.getMovieByGenre(genreBundle.genre)
            }
        })

        viewModel._movies.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        rvMovieList.visibility = View.VISIBLE
                        resource.data?.let {
                            adapter.addDataToExisting(it)
                        }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
//                        rvMovieList.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
//                        rvMovieList.visibility = View.GONE
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        })
    }

    override fun clickMovie(homeMovie: HomeMovie) {
       val action = MovieListFragmentDirections.goToDetail(homeMovie.id, homeMovie.video, homeMovie.coverUrl)
        viewModel._page = 1
        adapter.clearData()
        findNavController().navigate(action)
    }
}