package ir.zinutech.android.flickrsearch.features.search

import androidx.recyclerview.widget.DiffUtil
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto

object SearchItemDiffUtil : DiffUtil.ItemCallback<FlickrPhoto>() {
    override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.url == newItem.url
    }
}