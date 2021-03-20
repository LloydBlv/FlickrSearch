package ir.zinutech.android.flickrsearch.data.features.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FlickrPhotoDto(
        @Json(name = "id") val id: String,
        @Json(name = "server") val server: String,
        @Json(name = "farm") val farm: String,
        @Json(name = "title") val title: String,
)