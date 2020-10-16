package com.riztech.themovie.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.DaggerHomeComponent
import com.riztech.themovie.util.Status
import com.riztech.themovie.util.coreComponent
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), HomeAdapter.OnClickHomeItem {

    @Inject
    lateinit var viewModel: HomeViewModel

    lateinit var adapter: HomeAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerHomeComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val navHostFragment = NavHostFragment.findNavController(this);
//        NavigationUI.setupWithNavController(requireActivity(), navHostFragment)

        viewModel.getGenre()

        rvHomeMovie.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter(arrayListOf(), this)
//        rvHomeMovie.addItemDecoration(
//            DividerItemDecoration(
//                rvHomeMovie.context,
//                (rvHomeMovie.layoutManager as LinearLayoutManager).orientation
//            )
//        )
        rvHomeMovie.adapter = adapter

        viewModel._genre.observe(viewLifecycleOwner, Observer {
            it?.let {resource ->
                when (resource.status){
                    Status.SUCCESS ->{
                        progressBar.visibility = View.GONE
                        rvHomeMovie.visibility = View.VISIBLE
                        resource.data?.let {
                            adapter.addGenre(it)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    Status.LOADING->{
                        progressBar.visibility = View.VISIBLE
                        rvHomeMovie.visibility = View.GONE
                    }
                    Status.ERROR-> {
                        progressBar.visibility = View.GONE
                        rvHomeMovie.visibility = View.GONE
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })

    }

    override fun clickGenre(genreId: Int) {
        val action = HomeFragmentDirections.goToListMovie(genreId)
        findNavController().navigate(action)
    }

}