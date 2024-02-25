package ir.zinutech.android.flickrsearch.data.core.util

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class FlickrInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val originalHttpUrl: HttpUrl = chain.request().url
        val url = originalHttpUrl.newBuilder()
                .addQueryParameter(QUERY_PARAM_API_KEY, apiKey)
                .addQueryParameter(QUERY_PARAM_API_FORMAT, JSON_FORMAT)
                .addQueryParameter(QUERY_PARAM_NO_JSON_CALLBACK, NO_JSON)
                .build()

        proceed(request().newBuilder().url(url).build()
        )
    }

    companion object {
        private const val JSON_FORMAT = "json"
        private const val NO_JSON = "1"
        private const val QUERY_PARAM_API_FORMAT = "format"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_NO_JSON_CALLBACK = "nojsoncallback"
    }
}