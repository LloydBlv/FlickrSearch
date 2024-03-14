package ir.zinutech.android.flickrsearch.data.core.di

import javax.inject.Qualifier

@Qualifier
annotation class NetworkInterceptor

typealias DaggerSet<T> = @JvmSuppressWildcards Set<T>
