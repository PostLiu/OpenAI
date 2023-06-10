package com.postliu.openai.di

import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.domain.usecase.GetHomeGoodsUseCase
import com.postliu.openai.domain.usecase.IGetHomeGoodsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetHomeGoodsUseCaseModule {

    @Provides
    @Singleton
    fun providesGetHomeGoodsUseCase(repository: IHomeRepository): IGetHomeGoodsUseCase {
        return GetHomeGoodsUseCase(repository)
    }

}