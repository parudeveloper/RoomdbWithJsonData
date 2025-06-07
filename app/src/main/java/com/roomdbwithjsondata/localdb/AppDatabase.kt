package com.roomdbwithjsondata.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.roomdbwithjsondata.dao.CountryDao
import com.roomdbwithjsondata.model.Country

@Database(entities = [Country::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object{
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new column `flag` to the country_table
                database.execSQL("ALTER TABLE country_table ADD COLUMN countryFlag TEXT NOT NULL DEFAULT ''")
            }
        }
    }

}