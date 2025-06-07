package com.roomdbwithjsondata.jsonresponce

import com.google.gson.annotations.SerializedName
import com.roomdbwithjsondata.model.Country

data class CountryResponse(
    @SerializedName("cca3") val cca3: String,
    @SerializedName("name") val name: Name,
    @SerializedName("region") val region: String?,
    @SerializedName("subregion") val subregion: String?,
    @SerializedName("population") val population: Long?,
    @SerializedName("area") val area: Double?,
    @SerializedName("flag") val flagEmoji: String?,
    @SerializedName("flags") val flags: Flags
)

data class Name(
    @SerializedName("common") val common: String,
    @SerializedName("official") val official: String
)

data class Flags(
    @SerializedName("png") val png: String
)

// Convert response to entity
fun CountryResponse.toCountry() = Country(
    cca3 = cca3,
    commonName = name.common,
    officialName = name.official,
    region = region,
    subregion = subregion,
    population = population,
    area = area,
    flagEmoji = flagEmoji,
    countryFlag = flags.png
)