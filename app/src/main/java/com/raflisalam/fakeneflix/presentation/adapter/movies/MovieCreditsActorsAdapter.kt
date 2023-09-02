package com.raflisalam.fakeneflix.presentation.adapter.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.ItemMovieCreditsActorsBinding
import com.raflisalam.fakeneflix.domain.model.actors.ActorsMovieCredits

class MovieCreditsActorsAdapter(
    private var listMoviesCredits: List<ActorsMovieCredits>,
    private val onItemDataClickListener: OnItemDataClickListener
) : RecyclerView.Adapter<MovieCreditsActorsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMovieCreditsActorsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ActorsMovieCredits) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.moviesPoster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                title.text = item.moviesName
                nameCharacter.text = "as ${item.characterName}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieCreditsActorsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMoviesCredits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMoviesCredits[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemDataClickListener.onItemMoviesClick(item.moviesId)
        }
    }

}