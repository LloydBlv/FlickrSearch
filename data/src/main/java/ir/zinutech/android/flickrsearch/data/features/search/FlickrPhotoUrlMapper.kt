package ir.zinutech.android.flickrsearch.data.features.search

import ir.zinutech.android.flickrsearch.data.core.util.Mapper
import ir.zinutech.android.flickrsearch.domain.features.search.models.PhotoUrl
import javax.inject.Inject


class FlickrPhotoUrlMapper @Inject constructor() : Mapper<FlickrPhotoDto, PhotoUrl> {
    override fun map(input: FlickrPhotoDto): PhotoUrl {
        return URL_TEMPLATE.format(input.farm, input.server, input.id, input.secret)
    }

    companion object {
        private const val URL_TEMPLATE = "https://farm%s.staticflickr.com/%s/%s_%s.jpg"
    }
}