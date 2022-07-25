package com.example.dentrealitytestapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dentrealitytestapp.data.Repository
import com.example.dentrealitytestapp.data.RepositoryImpl
import com.example.dentrealitytestapp.models.CountriesModel
import com.example.dentrealitytestapp.models.toLocation
import com.google.maps.android.ktx.utils.sphericalDistance
import kotlin.collections.ArrayList


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var countries = MutableLiveData<ArrayList<CountriesModel>>()
    private var _countries = ArrayList<CountriesModel>()
    private var repository: Repository = RepositoryImpl(getApplication<Application>().applicationContext)

    init {
        _countries = repository.parseAndGetCountries()
        setCountries(_countries)
    }

    private fun setCountries(countries: ArrayList<CountriesModel>) {
        this.countries.value = countries
    }

    fun setUserCountry(userCountry: String) {
        updateCountriesDistance(userCountry)
    }

    private fun updateCountriesDistance(userCountry: String) {
        countries.value?.forEach { it.distance = calculateDistanceToEachCountry(userCountry) }
    }

    private fun calculateDistanceToEachCountry(userCountry: String): Double {
        val countryName = userCountry.capitalize()
        val country: CountriesModel? = countries.value?.find { it.name == countryName } //O(n)
        if (country != null) {
            countries.value?.forEach { it ->
                if (it == country) {
                    it.distance = 0.0
                } else {
                    it.distance = (it.toLocation()
                        .sphericalDistance(country.toLocation())).div(1000)
                }
            }
        }

        return 0.0
    }

    fun getCountries() = countries.value


}
