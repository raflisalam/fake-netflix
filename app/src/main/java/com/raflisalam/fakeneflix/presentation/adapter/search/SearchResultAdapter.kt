package com.raflisalam.fakeneflix.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raflisalam.fakeneflix.common.utils.OnItemDataClickListener
import com.raflisalam.fakeneflix.databinding.ItemSearchMovieResultBinding
import com.raflisalam.fakeneflix.databinding.ItemSearchPersonResultBinding
import com.raflisalam.fakeneflix.databinding.ItemSearchTvResultBinding
import com.raflisalam.fakeneflix.domain.model.search.MovieResult
import com.raflisalam.fakeneflix.domain.model.search.PersonResult
import com.raflisalam.fakeneflix.domain.model.search.TvShowResult
import com.raflisalam.fakeneflix.presentation.adapter.search.viewholder.MoviesSearchResultViewHolder
import com.raflisalam.fakeneflix.presentation.adapter.search.viewholder.PersonSearchResultViewHolder
import com.raflisalam.fakeneflix.presentation.adapter.search.viewholder.TvShowsSearchResultViewHolder

class SearchResultAdapter(
    private var items: List<Any>,
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_MOVIES_SEARCH_RESULT -> {
                val binding = ItemSearchMovieResultBinding.inflate(inflater, parent,false)
                MoviesSearchResultViewHolder(binding)
            }
            VIEW_TV_SHOW_SEARCH_RESULT -> {
                val binding = ItemSearchTvResultBinding.inflate(inflater, parent,false)
                TvShowsSearchResultViewHolder(binding)
            }
            VIEW_PERSON_SEARCH_RESULT -> {
                val binding = ItemSearchPersonResultBinding.inflate(inflater, parent,false)
                PersonSearchResultViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Type ViewHolder not valid")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_MOVIES_SEARCH_RESULT -> {
                val movies = items[position] as MovieResult
                val viewHolder = holder as MoviesSearchResultViewHolder
                viewHolder.bind(movies)
                viewHolder.itemView.setOnClickListener {
                    movies.id?.let { it1 -> onItemDataClickListener.onItemMoviesClick(it1) }
                }
            }
            VIEW_TV_SHOW_SEARCH_RESULT -> {
                val tvShows = items[position] as TvShowResult
                val viewHolder = holder as TvShowsSearchResultViewHolder
                viewHolder.bind(tvShows)
                viewHolder.itemView.setOnClickListener {
                    tvShows.id?.let { it1 -> onItemDataClickListener.onItemTvShowsClick(it1) }
                }
            }
            VIEW_PERSON_SEARCH_RESULT -> {
                val person = items[position] as PersonResult
                val viewHolder = holder as PersonSearchResultViewHolder
                viewHolder.bind(person)
                person.id?.let { it1 -> onItemDataClickListener.onItemActorsClick(it1) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieResult -> VIEW_MOVIES_SEARCH_RESULT
            is TvShowResult -> VIEW_TV_SHOW_SEARCH_RESULT
            is PersonResult -> VIEW_PERSON_SEARCH_RESULT
            else -> throw IllegalArgumentException("Type item not valid")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData: List<Any>) {
        this.items = newData
        notifyDataSetChanged()
    }


    companion object {
        private const val VIEW_MOVIES_SEARCH_RESULT = 1
        private const val VIEW_TV_SHOW_SEARCH_RESULT = 2
        private const val VIEW_PERSON_SEARCH_RESULT = 3
    }
}