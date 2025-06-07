package com.roomdbwithjsondata.module

import android.content.Context
import androidx.room.Room
import com.roomdbwithjsondata.dao.CountryDao
import com.roomdbwithjsondata.localdb.AppDatabase
import com.roomdbwithjsondata.localdb.AppDatabase.Companion.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    fun provideCountryDao(db: AppDatabase): CountryDao = db.countryDao()
}