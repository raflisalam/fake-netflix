package com.raflisalam.fakeneflix.presentation.adapter.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.data.remote.model.tv_shows.detail.SeasonDto
import com.raflisalam.fakeneflix.databinding.ItemSeasonTvshowsBinding
import com.raflisalam.fakeneflix.domain.model.tv_shows.Season

class SeasonSeriesAdapter(private val listSeason: List<Season>): RecyclerView.Adapter<SeasonSeriesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSeasonTvshowsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Season) {
            binding.apply {
                if (!item.poster.isNullOrEmpty()) {
                    val posterPath = "${Constant.path_image_base_url}${item.poster}"
                    Glide.with(itemView)
                        .load(posterPath)
                        .apply(RequestOptions().error(R.drawable.default_poster))
                        .into(tvPoster)
                } else {
                    tvPoster.setImageResource(R.drawable.default_poster)
                }
                if (item.number_season == 0) {
                    season.text = "${item.name}"
                } else {
                    season.text = "Seasons ${item.number_season}"
                }
                if (item.release_date.isNullOrEmpty()) {
                    date.text = ""
                } else {
                    date.text = "- ${item.release_date?.let { TimeUtils.formatDateToYears(it) }}"
                }
                ratingSeason.text = item.rating_season.toString() ?: "0.0"
                episodeSeason.text = "${item.episode_count} episodes"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeasonTvshowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSeason.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listSeason[position]
        holder.bind(item)
    }
}