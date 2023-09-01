package com.raflisalam.fakeneflix.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.ActorsIdStateFlow
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemClickListener
import com.raflisalam.fakeneflix.databinding.FragmentSearchBinding
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.presentation.adapter.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.adapter.PopularActorsAdapter
import com.raflisalam.fakeneflix.presentation.adapter.SearchResultAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailMoviesActivity
import com.raflisalam.fakeneflix.presentation.ui.details.actors.DetailActorsActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.ActorsViewModel
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val actorsViewModel: ActorsViewModel by viewModels()

    private lateinit var searchAdapter: SearchResultAdapter
    private lateinit var trendingMoviesAdapter: MoviesAdapter
    private lateinit var popularActorsAdapter: PopularActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchMovies()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchTrendingMovies()
        fetchPopularActors()
    }

    private fun searchMovies() {
        binding.apply {
            searchView.setOnClickListener { searchView.isIconified = false }
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.setQuery("", false)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isEmpty() == true) {
                        binding.textHeadTrending.visibility = View.VISIBLE
                        binding.textHeadPopularActors.visibility = View.VISIBLE
                        binding.rvPopularActors.visibility = View.VISIBLE
                        fetchTrendingMovies()
                        fetchPopularActors()
                    } else {
                        binding.textHeadTrending.visibility = View.GONE
                        binding.textHeadPopularActors.visibility = View.GONE
                        binding.rvPopularActors.visibility = View.GONE
                        if (newText != null) {
                            fetchSearchResult(newText)
                        }
                    }
                    return true
                }
            })
        }
    }

    private fun fetchPopularActors() {
        actorsViewModel.fetchActorsPopular()
        actorsViewModel.getActorsPopular.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    showPopularActors(data)
                }
                else -> {}
            }
        }
    }

    private fun showPopularActors(data: List<Actors>) {
        popularActorsAdapter = PopularActorsAdapter(data, this)
        binding.apply {
            rvPopularActors.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvPopularActors.adapter = popularActorsAdapter
        }

    }

    private fun fetchTrendingMovies() {
        moviesViewModel.fetchTrendingMovies()
        moviesViewModel.getTrendingMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    showTrendingMovies(data)
                }
                else -> {}
            }
        }
    }

    private fun showTrendingMovies(data: List<Movies>) {
        trendingMoviesAdapter = MoviesAdapter(data, this)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = trendingMoviesAdapter
        }
    }

    private fun fetchSearchResult(query: String) {
        moviesViewModel.searchMoviesByName(query)
        moviesViewModel.getMoviesByName.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    showSearchResult(data)
                }
                else -> {}
            }
        }
    }

    private fun showSearchResult(data: List<Movies>) {
        searchAdapter = SearchResultAdapter(data, this)
        searchAdapter.updateData(data)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = searchAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemMoviesClick(id: Int) {
        MoviesIdStateFlow.onMoviesSelected(id)
        showMovieDetails()
    }

    override fun onItemActorsClick(id: Int) {
        ActorsIdStateFlow.onActorsSelected(id)
        showActorsDetail()
    }

    private fun showActorsDetail() {
        startActivity(Intent(requireContext(), DetailActorsActivity::class.java))
    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailMoviesActivity::class.java))
    }
}