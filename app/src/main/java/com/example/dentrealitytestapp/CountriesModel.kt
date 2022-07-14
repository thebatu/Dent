package com.example.dentrealitytestapp
import com.google.gson.annotations.SerializedName;


data class CountriesModel(
    @SerializedName("timezones")
    val mTimezones: String,

    @SerializedName("latlng")
    val mLatLng: MutableList<String>,

    @SerializedName("name")
    val name: String,

    @SerializedName("country_code")
    val MCountryCode: String,

    @SerializedName("capital")
    val MCapital: String,
)
