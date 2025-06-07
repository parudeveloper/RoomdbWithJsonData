package com.roomdbwithjsondata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey val cca3: String,  // unique country code
    val commonName: String,
    val officialName: String,
    val region: String?,
    val subregion: String?,
    val population: Long?,
    val area: Double?,
    val flagEmoji: String?
)
