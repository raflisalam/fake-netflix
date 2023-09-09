package com.raflisalam.fakeneflix.presentation.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.ActorsIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.data.remote.model.movies.Genre
import com.raflisalam.fakeneflix.databinding.ActivityDetailsMoviesBinding
import com.raflisalam.fakeneflix.domain.model.movies.MovieDetails
import com.raflisalam.fakeneflix.domain.model.movies.WatchlistMovies
import com.raflisalam.fakeneflix.presentation.adapter.movies.MoviesCastAdapter
import com.raflisalam.fakeneflix.presentation.adapter.movies.MoviesRecommendationsAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.actors.DetailActorsActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.DetailsMoviesViewModel
import com.raflisalam.fakeneflix.presentation.viewmodel.WatchlistMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMoviesActivity : AppCompatActivity(), OnItemDataClickListener {

    private lateinit var binding: ActivityDetailsMoviesBinding
    private lateinit var actorAdapter: MoviesCastAdapter
    private lateinit var recommendationsAdapter: MoviesRecommendationsAdapter

    private val viewModel: DetailsMoviesViewModel by viewModels()
    private val watchlistViewModel: WatchlistMoviesViewModel by viewModels()

    private var watchlistState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFetchMoviesDetails()
        setupButton()
    }


    private fun setupButton() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnWatchTrailer.setOnClickListener {
                binding.imagePoster.visibility = View.INVISIBLE
                binding.btnWatchTrailer.visibility = View.INVISIBLE
                watchTrailerMovies()
            }
        }
    }

    private fun watchTrailerMovies() {
        binding.videoTrailer.visibility = View.VISIBLE
        viewModel.movieDetails.observe(this) { status ->
            when (status) {
                is Status.Success -> {
                    val movies = status.data?.videos?.results
                    val trailerVideo = movies?.find { it.type == "Trailer" && it.official } ?: movies?.find { it.type == "Trailer" && !it.official }

                    if (trailerVideo != null) {
                        val videoUrl = "${Constant.video_base_url}${trailerVideo.key}"
                        binding.apply {
                            videoTrailer.settings.javaScriptEnabled = true
                            videoTrailer.webViewClient = WebViewClient()
                            videoTrailer.webChromeClient = WebChromeClient()
                            videoTrailer.loadUrl(videoUrl)
                        }
                    }
                }
                else -> {}
            }
        }

    }


    private fun initFetchMoviesDetails() {
        viewModel.fetchMovieDetails()
        viewModel.movieDetails.observe(this) {
            when (it) {
                is Status.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Status.Success -> {
                    val movies = it.data
                    updateUI(movies)
                    binding.apply {
                        loading.visibility = View.GONE
                        textHeadSynopsis.visibility = View.VISIBLE
                        btnBack.visibility = View.VISIBLE
                        btnWatchTrailer.visibility = View.VISIBLE
                        iconRating.visibility = View.VISIBLE
                        iconTime.visibility = View.VISIBLE
                        rvActor.visibility = View.VISIBLE
                        rvRecommendations.visibility = View.VISIBLE
                        layoutAboutFilm.visibility = View.VISIBLE
                        textHeadActors.visibility = View.VISIBLE
                        btnWatchlist.visibility = View.VISIBLE
                    }
                }
                is Status.Error -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUI(data: MovieDetails?) {
        if (data != null) {
            binding.apply {
                Glide.with(this@DetailMoviesActivity)
                    .load("${Constant.path_image_base_url}${data.backdrop_poster}")
                    .apply(RequestOptions())
                    .into(imagePoster)
                titleMovies.text = data.title
                ratingMovies.text = "${data.rating} |"
                ratingVotes.text = data.rating_vote.toString()
                timeMovies.text = TimeUtils.formatRuntimeToHoursMinutes(data.runtime)
                synopsisMovies.text = data.synopsis
                originalTitle.text = data.original_title
                releaseDate.text = TimeUtils.formatDate(data.release_date)
                production.text = data.productionCountry[0].name
                tagline.text = data.tagline
                data.genres.forEach {
                    binding.genre.text = it.name
                    chipGenreMovies.addView(createChipGenre(it.name))
                }
                fetchActorMovies()
                fetchRecommendationsMovies()
                checkMoviesIsWatchlist(data)
                watchlistMovies(data)
            }
        }

    }

    private fun createChipGenre(name: String): Chip {
        val chip  = layoutInflater.inflate(R.layout.item_chip_genre, binding.chipGenreMovies, false) as Chip
        with(chip) {
            text = name
            isCheckedIconVisible = false
        }
        return chip
    }

    private fun checkMoviesIsWatchlist(data: MovieDetails) {
        lifecycleScope.launch {
            val isWatchlist = watchlistViewModel.watchlistMovies.firstOrNull()?.any { it.id == data.moviesId} == true
            if (isWatchlist) {
                watchlistState = true
                binding.btnWatchlist.isChecked = true
            } else {
                watchlistState = false
                binding.btnWatchlist.isChecked = false
            }
        }
    }

    private fun watchlistMovies(data: MovieDetails) {
        binding.btnWatchlist.setOnClickListener {
            val movies = WatchlistMovies(
                id = data.moviesId,
                title = data.title,
                image_poster = data.image_poster,
                description = data.synopsis,
                rating = data.rating,
                release_date = data.release_date
            )
            watchlistViewModel.toggleWatchlistMovies(movies)
        }
    }

    private fun fetchRecommendationsMovies() {
        viewModel.fetchRecommendationsMovies()
        viewModel.getRecommendationsMovies.observe(this) {
            when (it) {
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        binding.textHeadRecommendations.visibility = View.VISIBLE
                        recommendationsAdapter = MoviesRecommendationsAdapter(data)
                        initRecyclerViewRecommendations()
                    } else {
                        binding.textHeadRecommendations.visibility = View.INVISIBLE
                    }
                }
                else -> {}
            }
        }
    }

    private fun initRecyclerViewRecommendations() {
        binding.apply {
            rvRecommendations.layoutManager = LinearLayoutManager(this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            rvRecommendations.adapter = recommendationsAdapter
        }
    }

    private fun fetchActorMovies() {
        viewModel.fetchCreditsActorMovies()
        viewModel.getCreditsCastMovieMovies.observe(this) {
            when (it) {
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        actorAdapter = MoviesCastAdapter(data, this)
                        initRecyclerViewActor()
                    }
                }

                else -> {}
            }
        }
    }

    private fun initRecyclerViewActor() {
        binding.apply {
            rvActor.layoutManager = LinearLayoutManager(this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            rvActor.adapter = actorAdapter
        }
    }

    override fun onBackPressed() {
        if (binding.videoTrailer.canGoBack()) {
            binding.videoTrailer.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onItemMoviesClick(id: Int) {
    }

    override fun onItemActorsClick(id: Int) {
        ActorsIdStateFlow.onActorsSelected(id)
        showActorsDetail()
    }

    override fun onItemTvShowsClick(id: Int) {
    }

    private fun showActorsDetail() {
        startActivity(Intent(this, DetailActorsActivity::class.java))
    }

}