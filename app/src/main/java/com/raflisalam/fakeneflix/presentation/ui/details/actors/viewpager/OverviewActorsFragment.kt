package com.raflisalam.fakeneflix.presentation.ui.details.actors.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.databinding.FragmentOverviewActorsBinding
import com.raflisalam.fakeneflix.domain.model.actors.ActorsDetail
import com.raflisalam.fakeneflix.presentation.viewmodel.ActorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewActorsFragment : Fragment() {

    private var _binding: FragmentOverviewActorsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ActorsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewActorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fetchOverviewActors()

        return root
    }

    private fun fetchOverviewActors() {
        viewModel.fetchActorsDetail()
        viewModel.actorsDetail.observe(viewLifecycleOwner) {
            when (it) {
                is Status.Success -> {
                    val data = it.data
                    if (data != null) {
                        updateUI(data)
                    }
                }
                else -> {}
            }
        }
    }

    private fun updateUI(data: ActorsDetail) {
        binding.apply {
            birth.text = TimeUtils.formatDate(data.birthday)
            placeBirth.text = data.place_of_birth
            biography.text = data.bio
            deepLinkSocials(data)
        }
    }

    private fun deepLinkSocials(data: ActorsDetail) {
    }

}