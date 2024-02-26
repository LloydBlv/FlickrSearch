package ir.zinutech.android.flickrsearch.data.features.search

import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import ir.zinutech.android.flirckrsearch.core.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
        private val searchApi: SearchApi,
        private val searchDataMapper: SearchDataMapper,
        @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : SearchRepository {
    override suspend fun search(tag: String): List<FlickrPhoto> = withContext(ioDispatcher) {
        searchDataMapper.map(searchApi.search(tag))
    }
}