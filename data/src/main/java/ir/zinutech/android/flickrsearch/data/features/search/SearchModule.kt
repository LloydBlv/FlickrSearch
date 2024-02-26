package ir.zinutech.android.flickrsearch.data.features.search

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.zinutech.android.flickrsearch.data.core.util.ListMapper
import ir.zinutech.android.flickrsearch.data.core.util.Mapper
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.models.PhotoUrl
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {
    @Binds
    abstract fun provideSearchDataMapper(searchDataMapper: SearchDataMapper): ListMapper<FlickrPhotoDto, FlickrPhoto>

    @Binds
    abstract fun providePhotoUrlMapper(photoUrlMapper: FlickrPhotoUrlMapper): Mapper<FlickrPhotoDto, PhotoUrl>

    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideSearchApi(retrofit: Retrofit): SearchApi {
            return retrofit.create(SearchApi::class.java)
        }
    }
}