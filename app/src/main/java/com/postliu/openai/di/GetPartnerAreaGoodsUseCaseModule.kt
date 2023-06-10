package com.postliu.openai.di

import com.postliu.openai.domain.repository.IHomeRepository
import com.postliu.openai.domain.usecase.GetPartnerAreaGoodsUseCase
import com.postliu.openai.domain.usecase.IGetPartnerAreaGoodsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GetPartnerAreaGoodsUseCaseModule {

    @Provides
    @Singleton
    fun providesGetPartnerAreaGoodsUseCase(repository: IHomeRepository): IGetPartnerAreaGoodsUseCase {
        return GetPartnerAreaGoodsUseCase(repository)
    }
}