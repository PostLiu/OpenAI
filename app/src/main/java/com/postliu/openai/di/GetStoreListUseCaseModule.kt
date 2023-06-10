package com.postliu.openai.di

import com.postliu.openai.domain.repository.IStoreRepository
import com.postliu.openai.domain.usecase.GetStoreListUseCase
import com.postliu.openai.domain.usecase.IGetStoreListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetStoreListUseCaseModule {

    @Provides
    @Singleton
    fun providesGetStoreListUseCase(repository: IStoreRepository): IGetStoreListUseCase {
        return GetStoreListUseCase(repository)
    }
}