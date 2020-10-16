package com.riztech.themovie.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.riztech.themovie.R
import com.riztech.themovie.data.di.component.DaggerDetailComponent
import com.riztech.themovie.util.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.progressBar
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailViewModel

    lateinit var adapter: ReviewAdapter

    val movieBundle: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerDetailComponent.builder().coreComponent(coreComponent()).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetail(movieBundle.movieId)
        viewModel.getReviews(movieBundle.movieId)

        rvReview.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReviewAdapter(arrayListOf())
        rvReview.addItemDecoration(
            DividerItemDecoration(
                rvReview.context,
                (rvReview.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvReview.adapter = adapter

        rvReview.addOnScrollListener(object : EndlessOnScrollListener(){
            override fun onLoadMore() {
                viewModel.getReviews(movieBundle.movieId)
            }
        })


        ivPosterDetail.loadImage("http://image.tmdb.org/t/p/w185/${movieBundle.poster}", getProgressDrawable(requireContext()), requireContext())

        if (movieBundle.isHaveTrailer){
            viewModel.getTrailer(movieBundle.movieId)
        }else{
            ivPosterTrailer.loadImage("http://image.tmdb.org/t/p/w185/${movieBundle.poster}", getProgressDrawable(requireContext()), requireContext())
            ivPosterTrailer.visibility = View.VISIBLE
            trailer.visibility = View.INVISIBLE
        }



        viewModel._detail.observe(viewLifecycleOwner, Observer {
            it?.let {resource ->
                when (resource.status){
                    Status.SUCCESS ->{
                        progressBar.visibility = View.GONE
                        resource.data?.let {
                            tvTitle.setText(it.title)
                            tvOverview.setText(it.overview)
                        }
                    }
                    Status.LOADING->{
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR-> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })

        viewModel._trailer.observe(viewLifecycleOwner, Observer {
            it?.let {resource ->
                when (resource.status){
                    Status.SUCCESS ->{
                        ivPosterTrailer.visibility = View.GONE
                        resource.data?.let {
                            setUpYoutubePlayer(it.key)
                        }
                    }
                    Status.LOADING->{
                        ivPosterTrailer.visibility = View.VISIBLE
                        ivPosterTrailer.loadImage("http://image.tmdb.org/t/p/w185/${movieBundle.poster}", getProgressDrawable(requireContext()), requireContext())
                    }
                    Status.ERROR-> {
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })

        viewModel._reviews.observe(viewLifecycleOwner, Observer {
            it?.let {resource ->
                when (resource.status){
                    Status.SUCCESS ->{
                        resource.data?.let {
                            adapter.addDataToExisting(it)
                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR-> {
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        })
    }

    fun setUpYoutubePlayer(videoKey: String){
        trailer.getPlayerUiController().showFullscreenButton(true)
        trailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = videoKey
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

        trailer.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
            if (trailer.isFullScreen()) {
                trailer.exitFullScreen()
                requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                // Show ActionBar
                if (requireActivity().actionBar != null) {
                    requireActivity().actionBar!!.show()
                }
            } else {
                trailer.enterFullScreen()
                requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                // Hide ActionBar
                if (requireActivity().actionBar != null) {
                    requireActivity().actionBar!!.hide()
                }
            }
        })
    }

}