package ir.zinutech.android.flickrsearch.data.features.search

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository


@Module
@InstallIn(SingletonComponent::class)
interface SearchRepositoryModule {
    @Binds
    fun provideSearchRepository(repository: SearchRepositoryImpl): SearchRepository
}