package com.raflisalam.fakeneflix.presentation.ui.details.tv_shows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.common.utils.OnTvShowsItemClickListener
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.databinding.ActivityDetailTvShowsBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShowsDetail
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.SeasonSeriesAdapter
import com.raflisalam.fakeneflix.presentation.adapter.viewpager.ViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager.MovieCreditsActorsFragment
import com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager.OverviewActorsFragment
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.CastMoviesFragment
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.DirectedByFragment
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager.SeasonsSeriesFragment
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time

@AndroidEntryPoint
class DetailTvShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowsBinding

    private val viewModel: TvShowsViewModel by viewModels()

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
            CastMoviesFragment()
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
        }
    }
}