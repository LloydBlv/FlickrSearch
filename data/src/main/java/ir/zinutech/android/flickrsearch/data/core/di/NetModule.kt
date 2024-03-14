package ir.zinutech.android.flickrsearch.data.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import ir.zinutech.android.flickrsearch.data.core.util.FlickrInterceptor
import ir.zinutech.android.flickrsearch.domain.features.search.annotations.IsMainLooper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetModule {

    @NetworkInterceptor
    @Multibinds
    internal abstract fun provideNetworkInterceptors(): Set<Interceptor>

    @Multibinds
    internal abstract fun provideInterceptors(): Set<Interceptor>

    companion object {

        @Provides
        @Singleton
        @IntoSet
        internal fun provideFlickrInterceptor(@Named("flickr-api") apiKey: String): Interceptor = FlickrInterceptor(apiKey)

        @Provides
        @Singleton
        @IntoSet
        internal fun provideChucker(@ApplicationContext context: Context): Interceptor = ChuckerInterceptor.Builder(context).build()

        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                    .add(Wrapped.ADAPTER_FACTORY)
                    .build()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            interceptors: DaggerSet<Interceptor>,
            @IsMainLooper isLoopingOnMainLooper: Boolean,
            @NetworkInterceptor networkInterceptors: DaggerSet<Interceptor>
        ): OkHttpClient {
            if (isLoopingOnMainLooper) {
                throw IllegalStateException("HTTP client initialized on main thread.")
            }
            val builder = OkHttpClient.Builder().apply {
                networkInterceptors().addAll(networkInterceptors)
                interceptors().addAll(interceptors)
            }
            return builder.build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(client: Lazy<OkHttpClient>, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URI)
                    .callFactory { client.get().newCall(it) }
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
        }

        private const val BASE_URI = "https://api.flickr.com/services/rest/"
    }
}