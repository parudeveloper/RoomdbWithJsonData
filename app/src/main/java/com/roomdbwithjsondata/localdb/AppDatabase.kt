package com.roomdbwithjsondata.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roomdbwithjsondata.dao.CountryDao
import com.roomdbwithjsondata.model.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}