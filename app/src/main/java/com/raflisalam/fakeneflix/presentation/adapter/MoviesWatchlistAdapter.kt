package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import com.raflisalam.fakeneflix.databinding.ItemFavoriteMoviesBinding
import com.raflisalam.fakeneflix.domain.model.Movies

class MoviesWatchlistAdapter(
    private var listMovies: List<WatchlistMoviesEntity>,
    private val onItemMoviesClickListener: OnItemMoviesClickListener
): RecyclerView.Adapter<MoviesWatchlistAdapter.ViewHolder>() {

    class ViewHolder(private val binding:ItemFavoriteMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WatchlistMoviesEntity) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.posterUrl}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                titleMovies.text = item.title
                ratingMovies.text = item.rating.toString()
                releaseDate.text = item.release_date
                synopsisMovies.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesWatchlistAdapter.ViewHolder {
        val binding = ItemFavoriteMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemMoviesClickListener.onItemMoviesClick(item.id)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}