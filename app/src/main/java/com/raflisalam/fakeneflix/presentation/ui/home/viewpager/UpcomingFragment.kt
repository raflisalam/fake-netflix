package com.raflisalam.fakeneflix.presentation.ui.home.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.databinding.FragmentNowPlayingBinding
import com.raflisalam.fakeneflix.databinding.FragmentUpcomingBinding
import com.raflisalam.fakeneflix.presentation.adapter.MoviesPosterPagerAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesPosterPagerAdapter
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val images = listOf(
            R.drawable.image,
            R.drawable.poster_path4,
            R.drawable.poster_path5,
            R.drawable.poster_path6,
        )

        adapter = MoviesPosterPagerAdapter(images)
        binding.viewPager.adapter = adapter

        viewModel.fetchUpcomingMovies(1)
        viewModel.getUpcomingMovies.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Success -> {
                    val movies = status.data ?: emptyList()
                }
                else -> {}
            }
        }

        binding.apply {
            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.offscreenPageLimit = 3
            viewPager.currentItem = 1
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val pageTransformer = CompositePageTransformer()
            pageTransformer.addTransformer(MarginPageTransformer(20))
            pageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            viewPager.setPageTransformer(pageTransformer)
        }

        return root
    }

}