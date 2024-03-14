package ir.zinutech.android.flickrsearch.core.di

import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.zinutech.android.flickrsearch.BuildConfig
import ir.zinutech.android.flickrsearch.domain.features.search.annotations.IsMainLooper
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("flickr-api")
    fun provideFlickrApiKey(): String = BuildConfig.FLICKR_API_KEY

    @IsMainLooper
    @Provides
    fun isMainLooper(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }
}