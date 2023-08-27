package com.raflisalam.fakeneflix.presentation.ui.search

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.FragmentSearchBinding
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.presentation.adapter.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailsMoviesActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), OnItemMoviesClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchMovies()
    }

    private fun searchMovies() {
        binding.apply {
            searchView.setOnClickListener { searchView.isIconified = false }
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query?.isNotEmpty() == true) {
                        fetchSearchResult(query)
                    }
                    searchView.setQuery("", false)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }

            })
        }
    }

    private fun fetchSearchResult(query: String) {
        viewModel.searchMoviesByName(query)
        viewModel.getMoviesByName.observe(viewLifecycleOwner) {
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
        adapter = MoviesAdapter(data, this)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemMoviesClick(moviesId: Int) {
        MoviesIdStateFlow.onMoviesSelected(moviesId)
        showMovieDetails()
    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailsMoviesActivity::class.java))
    }
}