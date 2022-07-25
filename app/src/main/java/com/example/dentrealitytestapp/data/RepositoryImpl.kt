package com.example.dentrealitytestapp.data

import android.app.Application
import android.content.Context
import com.example.dentrealitytestapp.data.util.Converters
import com.example.dentrealitytestapp.data.util.GsonParser
import com.example.dentrealitytestapp.models.CountriesModel

class RepositoryImpl(
    private val context: Context
): Repository {

    override fun parseAndGetCountries(): ArrayList<CountriesModel> {
        val converter = Converters(GsonParser())
        return converter.fromCountriesJson(context)
    }
}