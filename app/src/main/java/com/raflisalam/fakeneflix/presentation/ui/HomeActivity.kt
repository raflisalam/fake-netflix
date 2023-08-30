package com.raflisalam.fakeneflix.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.databinding.ActivityHomeBinding
import com.raflisalam.fakeneflix.presentation.viewmodel.WatchlistMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: WatchlistMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        navView.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.watchlistMovies.collect { data ->
                var badge = navView.getOrCreateBadge(R.id.navigation_watchlist)
                badge.isVisible = data.isNotEmpty()
                badge.number = data.size
            }
        }
    }

}