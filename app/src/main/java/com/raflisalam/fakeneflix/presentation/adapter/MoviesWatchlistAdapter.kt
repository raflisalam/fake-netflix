import android.animation.Animator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.fakeneflix.R
import com.raflisalam.fakeneflix.common.Constant
import com.raflisalam.fakeneflix.common.utils.OnItemMoviesClickListener
import com.raflisalam.fakeneflix.data.local.entity.WatchlistMoviesEntity
import com.raflisalam.fakeneflix.databinding.ItemWatchlistMoviesBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MoviesWatchlistAdapter(
    private val onItemMoviesClickListener: OnItemMoviesClickListener
) : ListAdapter<WatchlistMoviesEntity, MoviesWatchlistAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWatchlistMoviesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WatchlistMoviesEntity) {
            binding.apply {
                val posterUrl = "${Constant.path_image_base_url}${item.posterUrl}"
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemWatchlistMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemMoviesClickListener.onItemMoviesClick(item.id)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WatchlistMoviesEntity>() {
            override fun areItemsTheSame(
                oldItem: WatchlistMoviesEntity,
                newItem: WatchlistMoviesEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WatchlistMoviesEntity,
                newItem: WatchlistMoviesEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}