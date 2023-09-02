package com.raflisalam.fakeneflix.presentation.adapter.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.ItemCastMoviesBinding
import com.raflisalam.fakeneflix.domain.model.credits.CastMovies

class MoviesCastAdapter(
    private var listCastMovies: List<CastMovies>,
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<MoviesCastAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCastMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastMovies) {
            binding.apply {
                val profileUrl = "${Constant.path_image_base_url}${item.profilePics}"
                if (item.profilePics.isBlank()) {
                    val defaultDrawableResId = R.drawable.default_profile
                    imageActor.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(profileUrl)
                        .apply(RequestOptions())
                        .into(imageActor)
                }
                nameActor.text = item.name
                nameCharacter.text = "as ${item.nameCharacter}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCastMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listCastMovies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemDataClickListener.onItemActorsClick(item.id)
        }
    }
}