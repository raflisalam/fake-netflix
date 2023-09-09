package com.raflisalam.fakeneflix.presentation.adapter.search.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.databinding.ItemSearchPersonResultBinding
import com.raflisalam.fakeneflix.domain.model.search.PersonResult


class PersonSearchResultViewHolder(val binding: ItemSearchPersonResultBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonResult) {
        binding.apply {
            val profileUrl = "${Constant.path_image_base_url}${item.profile_path}"
            if (item.profile_path.isNullOrEmpty()) {
                val defaultDrawableResId = R.drawable.default_poster
                profilePictures.setImageResource(defaultDrawableResId)
            } else {
                Glide.with(itemView.context)
                    .load(profileUrl)
                    .apply(RequestOptions())
                    .into(profilePictures)
            }
            nameActor.text = item.name
            chipGender.text = item.gender?.let { Convert.toGenders(it) }
            chipCareer.text = item.gender?.let { Convert.toCareers(it)}
        }
    }
}