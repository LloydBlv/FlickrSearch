package ir.zinutech.android.flickrsearch.data.features.search

import ir.zinutech.android.flickrsearch.data.core.util.ListMapper
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import javax.inject.Inject

class SearchDataMapper @Inject constructor(
        private val flickrPhotoUrlMapper: FlickrPhotoUrlMapper
) : ListMapper<FlickrPhotoDto, FlickrPhoto>() {
    override fun mapSingle(input: FlickrPhotoDto): FlickrPhoto {
        return FlickrPhoto(
                id = input.id.orEmpty(),
                title = input.title.orEmpty(),
                url = flickrPhotoUrlMapper.map(input)
        )
    }
}