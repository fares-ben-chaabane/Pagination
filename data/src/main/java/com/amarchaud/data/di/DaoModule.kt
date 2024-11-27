package com.amarchaud.data.di

import android.content.Context
import androidx.room.Room
import com.amarchaud.data.db.PaginationDemoDao
import com.amarchaud.data.db.PaginationDemoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context,
    ): PaginationDemoDb {
        return Room.databaseBuilder(appContext, PaginationDemoDb::class.java, "PaginationDemoDb")
            .build()
    }

    @Singleton
    @Provides
    fun provideComposeNetworkDao(PaginationDemoDb: PaginationDemoDb): PaginationDemoDao {
        return PaginationDemoDb.getPaginationDemoDao()
    }
}