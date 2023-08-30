package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.ItemMoviesTopRatedBinding
import com.raflisalam.fakeneflix.domain.model.movies.Movies

class MoviesTopRatedAdapter(
    private var listMoviesTopRated: List<Movies>,
    private val onItemMoviesClickListener: OnItemMoviesClickListener
): RecyclerView.Adapter<MoviesTopRatedAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMoviesTopRatedBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.poster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                title.text = item.title
                val ratingPercentage = (item.rating * 10).toInt()
                ratingBar.progress = ratingPercentage
                ratingValues.text = "$ratingPercentage%"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMoviesTopRated.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMoviesTopRated[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemMoviesClickListener.onItemMoviesClick(movie.id)
        }
    }

}