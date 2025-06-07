package com.roomdbwithjsondata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roomdbwithjsondata.model.Country
import com.roomdbwithjsondata.repo.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

        /*val countries: StateFlow<List<Country>> = repository.allCountries
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())*/

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        getAllCountries()
    }
    fun insertCountries(countries: List<Country>) = viewModelScope.launch {
        repository.insertCountries(countries)
    }

    fun getAllCountries() {
        viewModelScope.launch {
            repository.allCountries
                .collect { countryList ->
                    _countries.value = countryList
                }
        }
    }
}