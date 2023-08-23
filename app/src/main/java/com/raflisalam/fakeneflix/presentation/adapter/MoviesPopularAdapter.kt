package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.ItemMoviesPopularBinding
import com.raflisalam.fakeneflix.domain.model.Movies

class MoviesPopularAdapter(
    private var listMoviesPopular: List<Movies>,
    private val onItemMoviesClickListener: OnItemMoviesClickListener
) : RecyclerView.Adapter<MoviesPopularAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMoviesPopularBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constant.poster_base_url}${item.poster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                title.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMoviesPopular.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMoviesPopular[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemMoviesClickListener.onItemMoviesClick(item.id)
        }
    }

}