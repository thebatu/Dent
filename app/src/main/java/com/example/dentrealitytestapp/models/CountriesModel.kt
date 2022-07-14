package com.example.dentrealitytestapp.models
import com.google.gson.annotations.SerializedName
import com.google.android.gms.maps.model.LatLng



data class CountriesModel(
    @SerializedName("timezones")
    val mTimezones: MutableList<String>,

    @SerializedName("latlng")
    val mLatLng: MutableList<Double>,

    @SerializedName("name")
    val mName: String,

    @SerializedName("country_code")
    val MCountryCode: String,

    @SerializedName("capital")
    val MCapital: String,

    var mDistance: Double

)

fun CountriesModel.toLocation() :LatLng{
    return LatLng(mLatLng[0],mLatLng[1])
}
