package com.roomdbwithjsondata.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.roomdbwithjsondata.model.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Query("SELECT * FROM country_table ORDER BY commonName ASC")
    fun getAllCountries(): Flow<List<Country>>
}