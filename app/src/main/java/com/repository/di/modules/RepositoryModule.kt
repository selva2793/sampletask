package com.repository.di.modules

import com.repository.localdatabase.TrendingDao
import com.repository.api.ApiClient
import com.repository.api.TrendingRepoLocalDataSource
import com.repository.api.TrendingRepoRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesTrendingRemoteDataSource(apiClient:ApiClient): TrendingRepoRemoteDataSource =
        TrendingRepoRemoteDataSource(apiClient)

    @Provides
    @Singleton
    fun providesTrendingLocalDataSource(trendingDao: TrendingDao): TrendingRepoLocalDataSource =
        TrendingRepoLocalDataSource(trendingDao)
}