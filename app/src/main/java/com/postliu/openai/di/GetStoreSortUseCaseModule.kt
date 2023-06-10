package com.postliu.openai.di

import com.postliu.openai.domain.repository.IStoreRepository
import com.postliu.openai.domain.usecase.GetStoreSortUseCase
import com.postliu.openai.domain.usecase.IGetStoreSortUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetStoreSortUseCaseModule {

    @Provides
    @Singleton
    fun providesGetStoreSortUseCase(repository: IStoreRepository): IGetStoreSortUseCase {
        return GetStoreSortUseCase(repository)
    }
}