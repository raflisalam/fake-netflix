package com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.databinding.FragmentSeasonsSeriesBinding
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.SeasonSeriesAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonsSeriesFragment : Fragment() {

    private var _binding: FragmentSeasonsSeriesBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: SeasonSeriesAdapter
    private val viewModel: TvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasonsSeriesBinding.inflate(inflater, container, false)

        fetchSeasonSeries()

        return binding.root
    }

    private fun fetchSeasonSeries() {
        viewModel.fetchSeriesDetail()
        viewModel.seriesDetail.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val tvShowDetail = it.data
                    val season = tvShowDetail?.seasons
                    if (season != null) {
                        adapter = SeasonSeriesAdapter(season)
                        activity?.runOnUiThread {
                            showSeasonSeries()
                        }
                    }
                }
            }
        }
    }

    private fun showSeasonSeries() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
            recyclerView.invalidate()
        }
    }

}