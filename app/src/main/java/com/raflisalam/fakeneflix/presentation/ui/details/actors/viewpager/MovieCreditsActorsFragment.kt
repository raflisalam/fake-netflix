package com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.FragmentCreditsActorsBinding
import com.raflisalam.fakeneflix.presentation.adapter.movies.MovieCreditsActorsAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailMoviesActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.ActorsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieCreditsActorsFragment : Fragment(), OnItemDataClickListener {

    private var _binding: FragmentCreditsActorsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ActorsViewModel by viewModels()
    private lateinit var adapter: MovieCreditsActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreditsActorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fetchMovieCreditsActors()

        return root
    }
    private fun fetchMovieCreditsActors() {
        viewModel.fetchMovieCreditsActors()
        viewModel.getMovieCreditsActor.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    if (data != null) {
                        adapter = MovieCreditsActorsAdapter(data, this)
                        showMovieCreditsActors()
                    }
                }
            }
        }
    }

    private fun showMovieCreditsActors() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun onItemMoviesClick(id: Int) {
        MoviesIdStateFlow.onMoviesSelected(id)
        showMovieDetail()
    }

    private fun showMovieDetail() {
        startActivity(Intent(requireContext(), DetailMoviesActivity::class.java))
    }

    override fun onItemActorsClick(id: Int) {
    }

    override fun onItemTvShowsClick(id: Int) {

    }


}