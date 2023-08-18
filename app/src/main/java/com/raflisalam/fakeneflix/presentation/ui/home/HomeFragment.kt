package com.raflisalam.fakeneflix.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.databinding.FragmentHomeBinding
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.presentation.adapter.MoviesPopularAdapter
import com.raflisalam.fakeneflix.presentation.adapter.MoviesViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesPopularAdapter
    private lateinit var viewPagerAdapter: MoviesViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFetchMoviesPopular()
        initSetupViewPager()

    }


    private fun initSetupViewPager() {
        val images = listOf(
            R.drawable.image,
            R.drawable.poster_path4,
            R.drawable.poster_path5,
            R.drawable.poster_path6,
        )
        binding.apply {
            viewModel.fetchNowPlayingMovies(1)
            viewModel.getNowPlayingMovies.observe(viewLifecycleOwner) {
                when(it) {
                    is Status.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is Status.Loading -> {

                    }
                    is Status.Success -> {
                        val data = it.data ?: emptyList()
                        val viewPagerAdapt = MoviesViewPagerAdapter(data)
                        binding.viewPager.adapter = viewPagerAdapt
                    }
                }
            }

            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 3
            viewPager.currentItem = 1
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val pageTransformer = CompositePageTransformer()
            pageTransformer.addTransformer(MarginPageTransformer(20))
            pageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            viewPager.setPageTransformer(pageTransformer)
        }
    }

    private fun initFetchMoviesPopular() {
        viewModel.fetchPopularMovies(2)
        viewModel.getPopularMovies.observe(viewLifecycleOwner) { it ->
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    initRecycleView(data)
                }
            }
        }
    }

    private fun initRecycleView(data: List<Movies>) {
        adapter = MoviesPopularAdapter(data)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = MoviesPopularAdapter(data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}