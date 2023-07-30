package com.example.financemanager.di

import android.app.Application
import androidx.room.Room
import com.example.financemanager.data.local.FinanceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFinanceDatabase(
        app: Application
    ): FinanceDatabase {
        return Room
            .databaseBuilder(
                app,
                FinanceDatabase::class.java,
                "FinanceDatabase"
            ).build()
    }

}















