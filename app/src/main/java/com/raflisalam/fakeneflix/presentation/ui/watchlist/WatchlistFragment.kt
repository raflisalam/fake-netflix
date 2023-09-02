package com.raflisalam.fakeneflix.presentation.ui.watchlist

import com.raflisalam.fakeneflix.presentation.adapter.movies.MoviesWatchlistAdapter
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.FragmentWatchlistBinding
import com.raflisalam.fakeneflix.presentation.ui.details.DetailMoviesActivity
import com.raflisalam.fakeneflix.presentation.viewmodel.WatchlistMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchlistFragment : Fragment(), OnItemDataClickListener {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesWatchlistAdapter
    private val viewModel: WatchlistMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFetchWatchlistMovies()
        swipeToDelete()
    }

    private fun initFetchWatchlistMovies() {
        adapter = MoviesWatchlistAdapter( this@WatchlistFragment)
        lifecycleScope.launch {
            viewModel.watchlistMovies.collect { data ->
                adapter.submitList(data)
                showWatchlistMovies()
            }
        }
    }

    private fun showWatchlistMovies() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@WatchlistFragment.adapter
        }
    }

    private fun swipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movie = adapter.currentList[position]
                viewModel.removeFromWatchlist(movie.id)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                val swipeDecorator = RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_background_swipe))
                    .addActionIcon(R.drawable.ic_delete)
                    .setIconHorizontalMargin(resources.getDimensionPixelSize(R.dimen.icon_horizontal_margin))
                    .create()
                swipeDecorator.decorate()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    override fun onItemMoviesClick(id: Int) {
        MoviesIdStateFlow.onMoviesSelected(id)
        showMovieDetails()
    }

    override fun onItemActorsClick(id: Int) {

    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailMoviesActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
