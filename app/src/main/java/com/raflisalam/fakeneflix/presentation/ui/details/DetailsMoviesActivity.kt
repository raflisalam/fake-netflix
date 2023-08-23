package com.raflisalam.fakeneflix.presentation.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.data.remote.model.Genre
import com.raflisalam.fakeneflix.databinding.ActivityDetailsMoviesBinding
import com.raflisalam.fakeneflix.domain.model.MovieDetails
import com.raflisalam.fakeneflix.presentation.viewmodel.DetailsMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMoviesBinding
    private val viewModel: DetailsMoviesViewModel by viewModels()

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
                Toast.makeText(this@DetailsMoviesActivity, "TERTEKAN", Toast.LENGTH_LONG).show()
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
                Glide.with(this@DetailsMoviesActivity)
                    .load("${Constant.poster_base_url}${data.backdrop_poster}")
                    .apply(RequestOptions())
                    .into(imagePoster)
                titleMovies.text = data.title
                ratingMovies.text = "${data.rating} |"
                ratingVotes.text = data.rating_vote.toString()
                timeMovies.text = TimeUtils.formatRuntimeToHoursMinutes(data.runtime)
                synopsisMovies.text = data.synopsis
                setGenreMoviesInChipGroup(data.genres)
            }
        }

    }

    private fun setGenreMoviesInChipGroup(genres: List<Genre>?) {
        if (genres != null) {
            for (genre in genres) {
                val chip = Chip(this)
                chip.text = genre.name
                chip.textColors
                chip.isCheckedIconVisible = false
                chip.textSize = 12f // Ukuran teks dalam sp
                chip.setChipBackgroundColorResource(R.color.chip_background)
                chip.setTextColor(resources.getColor(R.color.chip_text, null))
                chip.layoutParams = ChipGroup.LayoutParams(
                    ChipGroup.LayoutParams.WRAP_CONTENT,
                    ChipGroup.LayoutParams.WRAP_CONTENT
                )
                binding.chipGenreMovies.addView(chip)
            }
        }
    }
}