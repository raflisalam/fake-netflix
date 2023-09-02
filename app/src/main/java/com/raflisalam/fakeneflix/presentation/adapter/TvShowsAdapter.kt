package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.databinding.ItemMoviesPopularBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.TvShows

class TvShowsAdapter (
    private var listTvShowsPopular: List<TvShows>
) : RecyclerView.Adapter<TvShowsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMoviesPopularBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TvShows) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.image_poster}"
                if (item.image_poster.isBlank()) {
                    val defaultDrawableResId = R.drawable.default_poster
                    imagePoster.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(posterUrl)
                        .apply(RequestOptions())
                        .into(imagePoster)
                }
                title.text = item.titleTvShows
                if (item.genresId.isNotEmpty()) {
                    genre.text = Convert.toGenres(item.genresId[0])
                } else {
                    genre.text = ""
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTvShowsPopular.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listTvShowsPopular[position]
        holder.bind(item)
    }

}