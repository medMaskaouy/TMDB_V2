package com.maskaouy.moviesviewer.data.di

import android.content.Context
import androidx.room.Room
import com.maskaouy.moviesviewer.data.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            AppDataBase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideMoviesDao(database: AppDataBase) = database.moviesDao()

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDataBase) = database.favoriteDao()
}