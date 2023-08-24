package com.raflisalam.fakeneflix.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.databinding.ItemActorMoviesBinding
import com.raflisalam.fakeneflix.domain.model.Actor

class MoviesActorAdapter(private var listActor: List<Actor>): RecyclerView.Adapter<MoviesActorAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemActorMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actor) {
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
        val binding = ItemActorMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listActor.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listActor[position]
        holder.bind(item)
    }
}