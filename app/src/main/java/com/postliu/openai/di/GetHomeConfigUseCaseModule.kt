package com.postliu.openai.di

import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.domain.usecase.GetHomeConfigUseCase
import com.postliu.openai.domain.usecase.IGetHomeConfigUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetHomeConfigUseCaseModule {

    @Provides
    @Singleton
    fun providesGetHomeConfigUseCase(repository: IHomeRepository): IGetHomeConfigUseCase {
        return GetHomeConfigUseCase(repository)
    }
}