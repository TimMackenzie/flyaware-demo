package com.defenseunicorns.flyaware.data.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlyAwareDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): FlyAwareDatabase {
        return Room.databaseBuilder(app, FlyAwareDatabase::class.java, "flyaware.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideIcaoDao(database: FlyAwareDatabase): IcaoDao {
        return database.icaoDao()
    }
}
