package ir.zinutech.android.flickrsearch.data.features.search

import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("?method=flickr.photos.search")
    @Wrapped(path = ["photos", "photo"])
    suspend fun search(@Query("tags") tags: String): List<FlickrPhotoDto>
}