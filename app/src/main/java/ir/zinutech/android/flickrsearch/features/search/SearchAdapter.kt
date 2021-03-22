package ir.zinutech.android.flickrsearch.features.search

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.zinutech.android.flickrsearch.R
import ir.zinutech.android.flickrsearch.core.extensions.inflater
import ir.zinutech.android.flickrsearch.databinding.ItemSearchResultLayoutBinding
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto

class SearchAdapter : ListAdapter<FlickrPhoto, SearchAdapter.ViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<FlickrPhoto>() {
            override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.url == newItem.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: ItemSearchResultLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(flickrPhoto: FlickrPhoto) {
            binding.run {
                photo = flickrPhoto
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val binding = ItemSearchResultLayoutBinding.inflate(parent.inflater(), parent, false)
                return ViewHolder(binding = binding)
            }
        }
    }
}