package com.example.dentrealitytestapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dentrealitytestapp.data.util.Converters
import com.example.dentrealitytestapp.data.util.GsonParser
import com.example.dentrealitytestapp.models.CountriesModel
import com.example.dentrealitytestapp.models.toLocation
import com.google.maps.android.ktx.utils.sphericalDistance
import kotlin.collections.ArrayList


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var countries = MutableLiveData<ArrayList<CountriesModel>>()
    private var _countries = ArrayList<CountriesModel>()

    init {
        _countries = parseAndGetCountries()
        setCountries(_countries)
    }

    private fun parseAndGetCountries(): ArrayList<CountriesModel> {
        val converter = Converters(GsonParser(), getApplication<Application>().applicationContext)
        return converter.fromCountriesJson(getApplication<Application>().applicationContext)
    }

    fun setUserCountry(userCountry: String) {
        if (countryIsValid(userCountry)) {
            updateCountriesDistance(userCountry)
            getCountries()
        }
    }

    private fun countryIsValid(userCountry: String): Boolean {
        return (userCountry.isNotEmpty() && userCountry.isNotBlank())
    }

    private fun updateCountriesDistance(userCountry: String) {
        countries.value?.forEach { it.mDistance = calculateDistanceToEachCountry(userCountry) }
    }

    private fun calculateDistanceToEachCountry(userCountry: String): Double {

        val countryName = userCountry.capitalize()
        val country: CountriesModel? = countries.value?.find { it.mName == countryName } //O(n)
        if (country != null) {
            countries.value?.forEach { it ->
                if (it == country) {
                    it.mDistance = 0.0
                } else {
                    it.mDistance = (it.toLocation()
                        .sphericalDistance(country.toLocation())).div(1000) //O(n)
                }
            }
        }

        return 0.0
    }


    private fun setCountries(country: ArrayList<CountriesModel>) {
        this.countries.value = country
    }

    fun getCountries() = countries.value




}
