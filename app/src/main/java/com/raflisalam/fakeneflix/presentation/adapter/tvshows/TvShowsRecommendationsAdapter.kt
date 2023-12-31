package com.raflisalam.fakeneflix.presentation.adapter.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.databinding.ItemMoviesRecommendationsBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows

class TvShowsRecommendationsAdapter(
    private var listMovies: List<TvShows>
) : RecyclerView.Adapter<TvShowsRecommendationsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMoviesRecommendationsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TvShows) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.image_poster}"
                if (item.image_poster.isNullOrEmpty()) {
                    val defaultDrawableResId = R.drawable.default_poster
                    imagePoster.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(posterUrl)
                        .apply(RequestOptions())
                        .into(imagePoster)
                }
                title.text = item.titleTvShows
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesRecommendationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }
}