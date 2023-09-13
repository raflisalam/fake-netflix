package com.raflisalam.fakeneflix.presentation.ui.search

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.enums.MediaType
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.common.utils.ActorsIdStateFlow
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.common.utils.SeriesIdStateFlow
import com.raflisalam.fakeneflix.databinding.FragmentSearchBinding
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.presentation.adapter.movies.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.adapter.discover.DiscoverAdapter
import com.raflisalam.fakeneflix.presentation.adapter.search.SearchResultAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailMoviesActivity
import com.raflisalam.fakeneflix.presentation.ui.details.actors.DetailActorsActivity
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.DetailTvShowsActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.DiscoverViewModel
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import com.raflisalam.fakeneflix.presentation.viewmodel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnItemDataClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val searchViewModel: SearchResultViewModel by viewModels()
    private val discoverViewModel: DiscoverViewModel by viewModels()

    private lateinit var searchAdapter: SearchResultAdapter
    private lateinit var discoverAdapter: DiscoverAdapter
    private lateinit var trendingMoviesAdapter: MoviesAdapter
    private var selectedGenres = ArrayList<String>()



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
        filterMovieTvShowsByGenres()
    }

    private fun filterMovieTvShowsByGenres() {
        binding.apply {
            val chipGroups = listOf(chipGenre1, chipGenre2, chipGenre3)

            for (chipGroup in chipGroups) {
                for (i in 0 until chipGroup.childCount) {
                    val chip = chipGroup.getChildAt(i) as Chip
                    chip.setOnCheckedChangeListener { button, isChecked ->
                        if (isChecked) {
                            val genresId = Convert.genresToInt(button.text.toString())
                            selectedGenres.add(genresId.toString())
                            getSelectedGenres(selectedGenres)
                            chip.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.chip_selected_color))
                        } else {
                            val genresId = Convert.genresToInt(button.text.toString())
                            selectedGenres.add(genresId.toString())
                            getSelectedGenres(selectedGenres)
                            chip.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.chip_inactive))
                        }
                    }
                }
            }
        }
    }

    private fun getSelectedGenres(selectedGenres: ArrayList<String>) {
        discoverViewModel.fetchDiscoverItemsByGenre(selectedGenres, MediaType.MOVIE)
        discoverViewModel.getDiscover.observe(viewLifecycleOwner) {
            showDiscover(it)
        }
    }


    private fun showDiscover(data: List<DiscoverResult>?) {
        if (data != null) {
            discoverAdapter = DiscoverAdapter(data, this)
            discoverAdapter.updateData(data)
            binding.apply {
                rvDiscover.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvDiscover.adapter = discoverAdapter
            }
        }
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
                        binding.textHeadCategories.visibility = View.VISIBLE
                        binding.chipGenre1.visibility = View.VISIBLE
                        binding.chipGenre2.visibility = View.VISIBLE
                        binding.chipGenre3.visibility = View.VISIBLE
                        fetchTrendingMovies()
                    } else {
                        binding.textHeadTrending.visibility = View.GONE
                        binding.textHeadCategories.visibility = View.GONE
                        binding.chipGenre1.visibility = View.GONE
                        binding.chipGenre2.visibility = View.GONE
                        binding.chipGenre3.visibility = View.GONE
                        if (newText != null) {
                            fetchSearchResult(newText)
                        }
                    }
                    return true
                }
            })
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
        searchViewModel.fetchSearchResult(query)
        searchViewModel.results.observe(viewLifecycleOwner) {
            showSearchResult(it)
        }
    }

    private fun showSearchResult(data: List<Any>) {
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

    override fun onItemTvShowsClick(id: Int) {
        SeriesIdStateFlow.onSeriesSelected(id)
        showTvShowsDetail()
    }

    private fun showTvShowsDetail() {
        startActivity(Intent(requireContext(), DetailTvShowsActivity::class.java))
    }

    private fun showActorsDetail() {
        startActivity(Intent(requireContext(), DetailActorsActivity::class.java))
    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailMoviesActivity::class.java))
    }
}