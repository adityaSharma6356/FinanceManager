package com.example.financemanager.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.financemanager.data.local.FinanceDatabase
import com.example.financemanager.data.local.SharedDao
import com.example.financemanager.data.local.SharedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideSharedDatabase(
        @ApplicationContext context: Context
    ): SharedDao {
        return SharedDatabase(context)
    }

}















