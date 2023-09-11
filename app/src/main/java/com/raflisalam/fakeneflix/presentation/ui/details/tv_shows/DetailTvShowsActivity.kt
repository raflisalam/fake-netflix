package com.raflisalam.fakeneflix.presentation.ui.details.tv_shows

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.databinding.ActivityDetailTvShowsBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.TvShowsRecommendationsAdapter
import com.raflisalam.fakeneflix.presentation.adapter.viewpager.ViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.CastTvShowsFragment
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.DirectedByFragment
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.SeasonsSeriesFragment
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowsBinding

    private val viewModel: TvShowsViewModel by viewModels()
    private lateinit var adapter: TvShowsRecommendationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFetchTvShowsDetail()
        setupButton()
        setupTabLayoutAndViewPager()
    }

    private fun setupButton() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupTabLayoutAndViewPager() {
        val fragmentList = listOf(
            SeasonsSeriesFragment(),
            DirectedByFragment(),
            CastTvShowsFragment()
        )
        binding.apply {
            val adapter = ViewPagerAdapter(this@DetailTvShowsActivity, fragmentList)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Seasons"
                    1 -> "Directed by"
                    else -> "Cast"
                }
            }.attach()
        }
    }

    private fun initFetchTvShowsDetail() {
        viewModel.fetchSeriesDetail()
        viewModel.seriesDetail.observe(this) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        updateUI(data)
                    }
                }
            }
        }
    }

    private fun updateUI(data: TvShowsDetail) {
        binding.apply {
            val imagePath = "${Constant.path_image_base_url}${data.background_poster}"
            Glide.with(this@DetailTvShowsActivity)
                .load(imagePath)
                .apply(RequestOptions())
                .into(imagePoster)
            titleSeries.text = data.titleTvShows
            ratingSeries.text = data.rating.toString()
            timeRelease.text = data.release_date?.let { TimeUtils.formatDateToYears(it) }
            overviewTvshows.text = data.overview
            season.text = "${data.number_of_seasons} seasons - ${data.number_of_episodes} episodes"
            originalTitle.text = data.original_name
            releaseDate.text = data.release_date?.let { TimeUtils.formatDate(it) }
            production.text = data.production_countries?.get(0)?.name ?: "Unknown"
            tagline.text = data.tagline
            data.genresId?.forEach {
                binding.genre.text = it.name
                chipGenreTv.addView(createChipGenre(it.name))
            }
            fetchRecommendationsTvShows()
        }
    }

    private fun createChipGenre(name: String): Chip {
        val chip  = layoutInflater.inflate(R.layout.item_chip_genre, binding.chipGenreTv, false) as Chip

        with(chip) {
            text = name
            isCheckedIconVisible = false
        }
        return chip
    }

    private fun fetchRecommendationsTvShows() {
        viewModel.fetchRecommendationsTvShows()
        viewModel.getRecommendationsTvShows.observe(this) {
            when (it) {
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        adapter = TvShowsRecommendationsAdapter(data)
                        initRecyclerViewRecommendations()
                    } else {
                    }
                }

                else -> {}
            }
        }
    }

    private fun initRecyclerViewRecommendations() {
        binding.apply {
            rvRecommendations.layoutManager = LinearLayoutManager(this@DetailTvShowsActivity, LinearLayoutManager.HORIZONTAL, false)
            rvRecommendations.adapter = adapter
        }
    }
}