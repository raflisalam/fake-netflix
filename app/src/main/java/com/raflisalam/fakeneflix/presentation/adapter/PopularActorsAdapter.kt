package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemClickListener
import com.raflisalam.fakeneflix.databinding.ItemActorsMoviesBinding
import com.raflisalam.fakeneflix.domain.model.actors.Actors

class PopularActorsAdapter(
    private var listActors: List<Actors>,
    private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<PopularActorsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemActorsMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actors) {
            binding.apply {
                val profileUrl = "${Constant.path_image_base_url}${item.profilePath}"
                if (item.profilePath.isBlank()) {
                    val defaultDrawableResId = R.drawable.default_profile
                    imageActor.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(profileUrl)
                        .apply(RequestOptions())
                        .into(imageActor)
                }
                nameActor.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActorsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listActors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listActors[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemActorsClick(item.id)
        }
    }
}