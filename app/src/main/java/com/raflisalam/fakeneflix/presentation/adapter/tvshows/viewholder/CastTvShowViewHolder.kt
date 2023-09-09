package com.raflisalam.fakeneflix.presentation.adapter.tvshows.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.databinding.ItemCastMoviesBinding
import com.raflisalam.fakeneflix.domain.model.credits.CastTvShow

class CastTvShowViewHolder(val binding: ItemCastMoviesBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CastTvShow) {
        binding.apply {
            val profileUrl = "${Constant.path_image_base_url}${item.profilePics}"
            if (item.profilePics.isNullOrEmpty()) {
                val defaultDrawableResId = R.drawable.default_profile
                imageActor.setImageResource(defaultDrawableResId)
            } else {
                Glide.with(itemView.context)
                    .load(profileUrl)
                    .apply(RequestOptions())
                    .into(imageActor)
            }
            nameActor.text = item.name
            nameCharacter.text = "as ${item.nameCharacter}"
        }
    }
}