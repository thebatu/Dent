package com.example.dentrealitytestapp.data

import com.example.dentrealitytestapp.models.CountriesModel

interface Repository {
    fun parseAndGetCountries(): ArrayList<CountriesModel>
}