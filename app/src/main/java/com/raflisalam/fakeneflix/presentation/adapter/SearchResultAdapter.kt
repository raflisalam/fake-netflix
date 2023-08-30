package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.databinding.ItemSearchResultBinding
import com.raflisalam.fakeneflix.domain.model.movies.Movies

class SearchResultAdapter(
    private var listMovies: List<Movies>,
    private val onItemMoviesClickListener: OnItemMoviesClickListener
): RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    fun updateData(newData: List<Movies>) {
        this.listMovies = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.poster}"
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemMoviesClickListener.onItemMoviesClick(item.id)
        }
    }
}