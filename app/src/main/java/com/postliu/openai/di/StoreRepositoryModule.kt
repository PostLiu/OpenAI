package com.postliu.openai.di

import com.postliu.openai.api.ApiService
import com.postliu.openai.domain.repository.IStoreRepository
import com.postliu.openai.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreRepositoryModule {

    @Provides
    @Singleton
    fun providesStoreRepository(apiService: ApiService): IStoreRepository {
        return StoreRepository(apiService)
    }
}