package com.raflisalam.fakeneflix.presentation.adapter.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.ItemViewPagerBinding
import com.raflisalam.fakeneflix.domain.model.movies.Movies

class MoviesPosterPagerAdapter(
    private var listMovies: List<Movies>?,
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<MoviesPosterPagerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movies) {
            val posterUrl = "${Constant.path_image_base_url}${movie.poster}"
            Glide.with(itemView.context)
                .load(posterUrl)
                .apply(RequestOptions())
                .into(binding.imagePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies?.get(position)
        if (movie != null) {
            holder.bind(movie)
            holder.itemView.setOnClickListener {
                onItemDataClickListener.onItemMoviesClick(movie.id)
            }
        }
    }
}