package com.raflisalam.fakeneflix.presentation.adapter.tvshows.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.databinding.ItemActorsMoviesBinding
import com.raflisalam.fakeneflix.databinding.ItemDirectedBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.CreatedBy

class CreatedByViewHolder(val binding: ItemDirectedBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CreatedBy) {
        binding.apply {
            val profileUrl = "${Constant.path_image_base_url}${item.profilePics}"
            if (item.profilePics.isNullOrEmpty()) {
                val defaultDrawableResId = R.drawable.default_profile
                imageDirected.setImageResource(defaultDrawableResId)
            } else {
                Glide.with(itemView.context)
                    .load(profileUrl)
                    .apply(RequestOptions())
                    .into(imageDirected)
            }
            nameDirected.text = item.name ?: "Tidak Ditampilkan"
        }
    }
}