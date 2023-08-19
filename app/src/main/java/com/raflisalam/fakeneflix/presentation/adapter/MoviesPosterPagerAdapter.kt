package com.raflisalam.fakeneflix.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.databinding.ItemViewPagerBinding

class MoviesPosterPagerAdapter(private var listMovies: List<Int>): RecyclerView.Adapter<MoviesPosterPagerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Int) {
            /*val posterUrl = "${Constant.poster_base_url}${movie.poster}"
            Glide.with(itemView.context)
                .load(posterUrl)
                .into(binding.imagePoster)*/
            binding.imagePoster.setImageResource(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }
}