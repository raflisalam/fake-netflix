package com.raflisalam.fakeneflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.raflisalam.fakeneflix.databinding.ActivityMainBinding
import com.raflisalam.fakeneflix.presentation.adapter.MoviesAdapter
import com.raflisalam.fakeneflix.presentation.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}