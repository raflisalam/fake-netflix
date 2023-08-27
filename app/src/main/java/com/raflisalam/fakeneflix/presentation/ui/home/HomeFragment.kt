package com.raflisalam.fakeneflix.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.FragmentHomeBinding
import com.raflisalam.fakeneflix.domain.model.Movies
import com.raflisalam.fakeneflix.presentation.adapter.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.adapter.MoviesTopRatedAdapter
import com.raflisalam.fakeneflix.presentation.adapter.ViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailsMoviesActivity
import com.raflisalam.fakeneflix.presentation.ui.home.viewpager.NowPlayingFragment
import com.raflisalam.fakeneflix.presentation.ui.home.viewpager.UpcomingFragment
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemMoviesClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var popularAdapter: MoviesAdapter
    private lateinit var topRatedAdapter: MoviesTopRatedAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupTabLayoutAndViewPager()
        return root
    }

    private fun setupTabLayoutAndViewPager() {
        val fragmentList = listOf(
            NowPlayingFragment(),
            UpcomingFragment()
        )
        binding.apply {
            val adapter = ViewPagerAdapter(requireActivity(), fragmentList)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Now Playing"
                    1 -> "Upcoming"
                    else -> ""
                }
            }.attach()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFetchMoviesPopular()
        initFetchMoviesTopRated()
        setDynamicBackground()
    }

    private fun initFetchMoviesTopRated() {
        viewModel.fetchTopRatedMovies(1)
        viewModel.getTopRatedMovies.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    initRecycleViewTopRatedMovies(data)
                }
            }
        }
    }

    private fun initRecycleViewTopRatedMovies(data: List<Movies>) {
        topRatedAdapter = MoviesTopRatedAdapter(data, this)
        binding.apply {
            rvTopRated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvTopRated.adapter = topRatedAdapter
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
                    initRecycleViewPopularMovies(data)
                }
            }
        }
    }

    private fun initRecycleViewPopularMovies(data: List<Movies>) {
        popularAdapter = MoviesAdapter(data, this)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = popularAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun setDynamicBackground() {
        viewModel.updateBackgroundLayout()
        viewModel.getBackgroundUrl.observe(viewLifecycleOwner) { backgroundUrl ->
            GlobalScope.launch(Dispatchers.IO) {
                val bitmap = Picasso.get().load(backgroundUrl).get()

                launch(Dispatchers.Main) {
                    Blurry.with(requireContext())
                        .radius(20)
                        .sampling(1)
                        .from(bitmap)
                        .into(binding.background)
                }
            }
        }
    }

    override fun onItemMoviesClick(moviesId: Int) {
        MoviesIdStateFlow.onMoviesSelected(moviesId)
        showMovieDetails()
    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailsMoviesActivity::class.java))
    }
}