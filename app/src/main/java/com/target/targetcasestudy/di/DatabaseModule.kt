package com.target.targetcasestudy.di

import android.app.Application
import androidx.room.Room
import com.target.targetcasestudy.database.DealDatabase
import com.target.targetcasestudy.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, DealDatabase::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDealDao(db: DealDatabase) = db.dealDao()
}