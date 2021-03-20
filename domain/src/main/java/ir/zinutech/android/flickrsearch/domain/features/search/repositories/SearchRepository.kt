package ir.zinutech.android.flickrsearch.domain.features.search.repositories

import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto

interface SearchRepository {
    suspend fun search(tag: String): List<FlickrPhoto>
}