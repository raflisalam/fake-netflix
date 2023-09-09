package com.raflisalam.fakeneflix.presentation.adapter.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raflisalam.fakeneflix.databinding.ItemActorsMoviesBinding
import com.raflisalam.fakeneflix.databinding.ItemCastMoviesBinding
import com.raflisalam.fakeneflix.databinding.ItemDirectedBinding
import com.raflisalam.fakeneflix.domain.model.credits.CastTvShow
import com.raflisalam.fakeneflix.domain.model.tv_shows.CreatedBy
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.viewholder.CastTvShowViewHolder
import com.raflisalam.fakeneflix.presentation.adapter.tvshows.viewholder.CreatedByViewHolder

class BaseTvShowAdapter(private val items: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CastTvShow -> VIEW_CAST_TV_SHOW
            is CreatedBy -> VIEW_CREATED_BY
            else -> throw IllegalArgumentException("Type item not valid")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_CAST_TV_SHOW -> {
                val binding = ItemCastMoviesBinding.inflate(inflater, parent,false)
                CastTvShowViewHolder(binding)
            }
            VIEW_CREATED_BY -> {
                val binding = ItemDirectedBinding.inflate(inflater, parent,false)
                CreatedByViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Type ViewHolder not valid")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_CAST_TV_SHOW -> {
                val castTvShow = items[position] as CastTvShow
                val viewHolder = holder as CastTvShowViewHolder
                viewHolder.bind(castTvShow)
            }
            VIEW_CREATED_BY -> {
                val createdBy = items[position] as CreatedBy
                val viewHolder = holder as CreatedByViewHolder
                viewHolder.bind(createdBy)
            }
        }
    }

    companion object {
        private const val VIEW_CAST_TV_SHOW = 1
        private const val VIEW_CREATED_BY = 2
    }

}