package com.raflisalam.fakeneflix.presentation.adapter.search.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.databinding.ItemSearchTvResultBinding
import com.raflisalam.fakeneflix.domain.model.search.TvShowResult

class TvShowsSearchResultViewHolder(val binding: ItemSearchTvResultBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TvShowResult) {
        binding.apply {
            val posterUrl = "${Constant.path_image_base_url}${item.poster_path}"
            if (item.poster_path.isNullOrEmpty()) {
                val defaultDrawableResId = R.drawable.default_poster
                imagePoster.setImageResource(defaultDrawableResId)
            } else {
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
            }
            titleMovies.text = item.name
            ratingMovies.text = item.vote_average?.let { Convert.roundDouble(it).toString() }
            if (item.genre_ids.isNullOrEmpty()) {
                chipGenre.visibility = View.GONE
            } else {
                chipGenre.text = Convert.toGenres(item.genre_ids[0])
            }
            releaseDate.text = item.first_air_date?.let { TimeUtils.formatDate(it) }
        }
    }
}