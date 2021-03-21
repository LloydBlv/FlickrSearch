package ir.zinutech.android.flickrsearch.core.di

import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.zinutech.android.flickrsearch.BuildConfig
import ir.zinutech.android.flirckrsearch.core.di.DispatchersModule
import ir.zinutech.android.flirckrsearch.core.di.IsMainLooper
import javax.inject.Named


@Module(includes = [DispatchersModule::class])
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