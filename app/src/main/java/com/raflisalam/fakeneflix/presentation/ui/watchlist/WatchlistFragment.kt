package com.raflisalam.fakeneflix.presentation.ui.watchlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.FragmentWatchlistBinding
import com.raflisalam.fakeneflix.presentation.adapter.MoviesWatchlistAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailsMoviesActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.WatchlistMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchlistFragment : Fragment(), OnItemMoviesClickListener {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WatchlistMoviesViewModel by viewModels()
    private lateinit var adapter: MoviesWatchlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFetchWatchlistMovies()
    }

    private fun initFetchWatchlistMovies() {
        lifecycleScope.launch {
            viewModel.watchlistMovies.collect { data ->
                    adapter = MoviesWatchlistAdapter(data, this@WatchlistFragment)
                    showWatchlistMovies()
            }
        }
    }

    private fun showWatchlistMovies() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun onItemMoviesClick(moviesId: Int) {
        MoviesIdStateFlow.onMoviesSelected(moviesId)
        showMovieDetails()
    }
    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailsMoviesActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

