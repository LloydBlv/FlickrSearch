package ir.zinutech.android.flickrsearch.data.features.search

import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
        private val searchApi: SearchApi,
        private val searchDataMapper: SearchDataMapper) : SearchRepository {
    override suspend fun search(tag: String): List<FlickrPhoto> {
        return searchDataMapper.map(searchApi.search(tag))
    }
}