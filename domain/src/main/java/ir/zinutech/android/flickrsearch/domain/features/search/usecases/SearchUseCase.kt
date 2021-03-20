package ir.zinutech.android.flickrsearch.domain.features.search.usecases

import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(tag: String): List<FlickrPhoto> {
        return searchRepository.search(tag)
    }
}