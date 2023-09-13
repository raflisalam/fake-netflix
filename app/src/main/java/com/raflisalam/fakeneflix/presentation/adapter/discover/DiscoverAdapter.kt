package com.raflisalam.fakeneflix.presentation.adapter.discover

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.enums.MediaType.*
import com.raflisalam.fakeneflix.common.helper.Convert
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.common.utils.TimeUtils
import com.raflisalam.fakeneflix.databinding.ItemDiscoverBinding
import com.raflisalam.fakeneflix.domain.model.discover.DiscoverResult
import kotlinx.coroutines.delay

class DiscoverAdapter(
    private var items: List<DiscoverResult>,
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemDiscoverBinding): RecyclerView.ViewHolder(binding.root) {

         fun bind(item: DiscoverResult) {
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
                titleMovies.text = item.title
                ratingMovies.text = item.vote_average?.let { Convert.roundDouble(it).toString() }
                if (item.release_date.isNullOrEmpty()) {
                    releaseDate.text = item.first_air_date?.let { TimeUtils.formatDate(it) }
                } else if (item.first_air_date.isNullOrEmpty()) {
                    releaseDate.text = item.release_date.let { TimeUtils.formatDate(it) }
                }
                chipGenre.removeAllViews() // Clear existing chips

                item.genre_ids?.let { genreIds ->
                    val genres = Convert.genresListIdToListString(genreIds)

                    for (genre in genres) {
                        val chip = createChipGenre(genre)
                        chipGenre.addView(chip)
                    }
                }
            }
         }

        private fun createChipGenre(genre: String): Chip {
            val chip  = LayoutInflater.from(itemView.context).inflate(R.layout.item_chip_genre_discover, binding.chipGenre, false) as Chip
            chip.text = genre
            return chip
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData: List<DiscoverResult>) {
        this.items = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            when (item.media_type) {
                MOVIE -> {
                    item.id?.let { it1 -> onItemDataClickListener.onItemMoviesClick(it1) }
                }
                TV_SHOW -> {
                    item.id?.let { it1 -> onItemDataClickListener.onItemTvShowsClick(it1) }
                }
            }
        }
    }
}