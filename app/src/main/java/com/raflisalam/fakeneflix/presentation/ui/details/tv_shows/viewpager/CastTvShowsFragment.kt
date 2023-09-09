package com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.databinding.FragmentCastTvshowsBinding
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.BaseTvShowAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CastTvShowsFragment : Fragment() {

    private var _binding: FragmentCastTvshowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BaseTvShowAdapter
    private val viewModel: TvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCastTvshowsBinding.inflate(inflater, container, false)


        fetchCastTvShows()

        return binding.root
    }

    private fun fetchCastTvShows() {
        viewModel.fetchSeriesDetail()
        viewModel.seriesDetail.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val tvShowDetail = it.data?.credits
                    val castList = tvShowDetail?.cast
                    if (castList != null) {
                        adapter = BaseTvShowAdapter(castList)
                        activity?.runOnUiThread {
                            showCastTvShows()
                        }
                    }
                }
            }
        }
    }

    private fun showCastTvShows() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
            recyclerView.invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}