package com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.databinding.FragmentCastTvshowsBinding
import com.raflisalam.fakeneflix.databinding.FragmentDirectedByBinding
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.BaseTvShowAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DirectedByFragment : Fragment() {

    private var _binding: FragmentDirectedByBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BaseTvShowAdapter
    private val viewModel: TvShowsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectedByBinding.inflate(inflater, container, false)

        fetchDirectedBy()

        return binding.root
    }

    private fun fetchDirectedBy() {
        viewModel.fetchSeriesDetail()
        viewModel.seriesDetail.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val tvShowDetail = it.data
                    val directed = tvShowDetail?.createdBy
                    Log.d("DIRECTED", directed.toString())
                    if (directed != null) {
                        adapter = BaseTvShowAdapter(directed)
                        activity?.runOnUiThread {
                            showDirectedBy()
                        }
                    }
                }
            }
        }
    }

    private fun showDirectedBy() {
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