package com.roomdbwithjsondata.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.roomdbwithjsondata.R
import com.roomdbwithjsondata.adapter.CountryAdapter
import com.roomdbwithjsondata.databinding.ActivityMainBinding
import com.roomdbwithjsondata.jsonresponce.CountryResponse
import com.roomdbwithjsondata.jsonresponce.toCountry
import com.roomdbwithjsondata.utility.readRawResource
import com.roomdbwithjsondata.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: CountryViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonString = this.readRawResource(R.raw.countries)

        val gson = Gson()
        val countryResponseList: Array<CountryResponse> = gson.fromJson(jsonString, Array<CountryResponse>::class.java)

        val countryList = countryResponseList.map { it.toCountry() }

        Log.d("CountryList", "Countries: ${countryList[0]}")
        Log.d("CountryList", "Countries: ${countryList.size}")


        // Insert into DB via ViewModel
        viewModel.insertCountries(countryList)

        val adapter = CountryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.countries.collect { countries ->
                // update UI with country list
                adapter.submitList(countries)
            }
        }
    }
}