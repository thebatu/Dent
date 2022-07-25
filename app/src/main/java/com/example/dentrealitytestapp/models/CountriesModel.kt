package com.example.dentrealitytestapp.models
import com.google.gson.annotations.SerializedName
import com.google.android.gms.maps.model.LatLng



data class CountriesModel(
    @SerializedName("timezones")
    val timezones: MutableList<String>,

    @SerializedName("latlng")
    val latLng: MutableList<Double>,

    @SerializedName("name")
    val name: String,

    @SerializedName("country_code")
    val countryCode: String,

    @SerializedName("capital")
    val capital: String,

    var distance: Double

)

fun CountriesModel.toLocation() :LatLng{
    return LatLng(latLng[0],latLng[1])
}
