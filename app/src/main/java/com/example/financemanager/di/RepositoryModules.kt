package com.example.financemanager.di

import com.example.financemanager.data.repositories.FinanceRepositoryImpl
import com.example.financemanager.domain.repositories.FinancesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    @Singleton
    abstract fun provideFinanceRepository(
        financeRepositoryImpl: FinanceRepositoryImpl
    ): FinancesRepository

}











