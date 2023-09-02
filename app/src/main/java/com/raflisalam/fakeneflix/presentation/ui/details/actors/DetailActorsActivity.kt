package com.raflisalam.fakeneflix.presentation.ui.details.actors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.databinding.ActivityDetailActorsBinding
import com.raflisalam.fakeneflix.domain.model.actors.ActorsDetail
import com.raflisalam.fakeneflix.presentation.adapter.viewpager.ViewPagerAdapter
import com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager.MovieCreditsActorsFragment
import com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager.OverviewActorsFragment
import com.raflisalam.fakeneflix.presentation.viewmodel.ActorsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailActorsBinding

    private val viewModel: ActorsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFetchActorsDetail()
        setupButton()
        setupTabLayoutAndViewPager()
    }

    private fun setupButton() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setupTabLayoutAndViewPager() {
        val fragmentList = listOf(
            OverviewActorsFragment(),
            MovieCreditsActorsFragment()
        )
        binding.apply {
            val adapter = ViewPagerAdapter(this@DetailActorsActivity, fragmentList)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Overview"
                    1 -> "Movies and TV Shows"
                    else -> ""
                }
            }.attach()
        }
    }

    private fun initFetchActorsDetail() {
        viewModel.fetchActorsDetail()
        viewModel.actorsDetail.observe(this) {
            when (it) {
                is Status.Error -> {

                }
                is Status.Loading -> {

                }
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        updateUI(data)
                    }
                }
            }
        }
    }

    private fun updateUI(data: ActorsDetail) {
        binding.apply {
            makeBackdropBlur(data)
            nameActor.text = data.name
            chipGender.text = Convert.toGenders(data.gender)
            chipCareer.text = Convert.toCareers(data.gender)
            val profileUrl = "${Constant.path_image_base_url}${data.profile_path}"
            Glide.with(this@DetailActorsActivity)
                .load(profileUrl)
                .apply(RequestOptions())
                .into(imageProfile)
        }
    }

    private fun makeBackdropBlur(data: ActorsDetail?) {
        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val url = "${Constant.path_image_base_url}${data?.profile_path}"
                val bitmap = Picasso.get().load(url).get()
                launch(Dispatchers.Main) {
                    Blurry.with(this@DetailActorsActivity)
                        .radius(25)
                        .sampling(1)
                        .from(bitmap)
                        .into(backdropProfile)
                }
            }
        }
    }
}