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
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.common.utils.OnTvShowsItemClickListener
import com.raflisalam.fakeneflix.common.utils.SeriesIdStateFlow
import com.raflisalam.fakeneflix.databinding.FragmentHomeBinding
import com.raflisalam.fakeneflix.domain.model.movies.Movies
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.TvShowsAdapter
import com.raflisalam.fakeneflix.presentation.adapter.movies.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.adapter.viewpager.ViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.DetailMoviesActivity
import com.raflisalam.fakeneflix.presentation.ui.details.tv_shows.DetailTvShowsActivity
import com.raflisalam.fakeneflix.presentation.ui.home.viewpager.NowPlayingFragment
import com.raflisalam.fakeneflix.presentation.ui.home.viewpager.UpcomingFragment
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel
import com.raflisalam.fakeneflix.presentation.viewmodel.TvShowsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemDataClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()
    private val tvShowsViewModel: TvShowsViewModel by viewModels()
    private lateinit var popularMovies: MoviesAdapter
    private lateinit var popularTvShows: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setupButton()

        setupTabLayoutAndViewPager()
        return root
    }

    private fun setupButton() {
        binding.background.setOnClickListener {
            startActivity(Intent(context, DetailMoviesActivity::class.java))
        }
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
        initFetchMoviesTvShows()
        setDynamicBackground()
    }

    private fun initFetchMoviesTvShows() {
        tvShowsViewModel.fetchPopularTvShows(1)
        tvShowsViewModel.getPopularTvShows.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data ?: emptyList()
                    initRecycleViewPopularTvShows(data)
                }
            }
        }
    }

    private fun initRecycleViewPopularTvShows(data: List<TvShows>) {
        popularTvShows = TvShowsAdapter(data, this)
        binding.apply {
            rvPopularTvShows.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvPopularTvShows.adapter = popularTvShows
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
        popularMovies = MoviesAdapter(data, this)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = popularMovies
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

    override fun onItemMoviesClick(id: Int) {
        MoviesIdStateFlow.onMoviesSelected(id)
        showMovieDetails()
    }

    override fun onItemActorsClick(id: Int) {

    }

    private fun showMovieDetails() {
        startActivity(Intent(requireContext(), DetailMoviesActivity::class.java))
    }

    override fun onItemTvShowsClick(id: Int) {
        SeriesIdStateFlow.onSeriesSelected(id)
        showSeriesDetail()
    }

    private fun showSeriesDetail() {
        startActivity(Intent(requireContext(), DetailTvShowsActivity::class.java))
    }
}