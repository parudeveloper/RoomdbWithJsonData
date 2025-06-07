package com.roomdbwithjsondata.repo

import com.roomdbwithjsondata.dao.CountryDao
import com.roomdbwithjsondata.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val countryDao: CountryDao
) {
    val allCountries: Flow<List<Country>> = countryDao.getAllCountries()

    suspend fun insertCountries(countries: List<Country>) {
        countryDao.insertAll(countries)
    }
}