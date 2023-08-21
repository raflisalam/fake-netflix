package com.raflisalam.fakeneflix.presentation.ui.home.viewpager

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.PositionPageFlow
import com.raflisalam.fakeneflix.databinding.FragmentNowPlayingBinding
import com.raflisalam.fakeneflix.presentation.adapter.MoviesPosterPagerAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesPosterPagerAdapter
    private val viewModel: MoviesViewModel by viewModels()

    private val autoSlideHandler = Handler()
    private var isAutoSlideRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.fetchNowPlayingMovies(1)
        viewModel.getNowPlayingMovies.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Success -> {
                    val movies = status.data
                    adapter = MoviesPosterPagerAdapter(movies)
                    binding.viewPager.adapter = adapter
                }
                else -> {}
            }
        }
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.apply {
            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 3
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val pageTransformer = CompositePageTransformer()
            pageTransformer.addTransformer(MarginPageTransformer(20))
            pageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            viewPager.setPageTransformer(pageTransformer)

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    PositionPageFlow.onPageSelected("NowPlaying", position)
                }
            })
        }
        startAutoSlideNowPlayingMovies()
    }

    private val autoSlideRunnable = object : Runnable {
        override fun run() {
            if (isAutoSlideRunning) {
                val currentItem = binding.viewPager.currentItem
                val itemCount = binding.viewPager.adapter?.itemCount ?: 0

                binding.viewPager.setCurrentItem((currentItem + 1) % itemCount, true)
                autoSlideHandler.postDelayed(this, 2500)
            }
        }
    }

    private fun startAutoSlideNowPlayingMovies() {
        isAutoSlideRunning = true
        autoSlideHandler.postDelayed(autoSlideRunnable, 2500)
    }

    private fun stopAutoSlideNowPlayingMovies() {
        isAutoSlideRunning = false
        autoSlideHandler.removeCallbacks(autoSlideRunnable)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        autoSlideHandler.removeCallbacks(autoSlideRunnable)
    }

    override fun onResume() {
        super.onResume()
        if (!isAutoSlideRunning) {
            startAutoSlideNowPlayingMovies()
        }
    }

    override fun onPause() {
        super.onPause()
        stopAutoSlideNowPlayingMovies()
    }

}