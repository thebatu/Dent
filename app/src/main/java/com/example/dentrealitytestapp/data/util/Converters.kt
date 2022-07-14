package com.example.dentrealitytestapp.data.util

import android.content.Context
import com.example.dentrealitytestapp.Constants
import com.example.dentrealitytestapp.data.JsonParser
import com.example.dentrealitytestapp.models.CountriesModel
import com.google.gson.reflect.TypeToken

class Converters(
    private val jsonParser: JsonParser,
    private val context: Context
) {

    private fun jsonFileString(context: Context): String {
        val jsonString = Utils.GetCountries.getJsonDataFromAsset(
            context, Constants.JSON_FILE_NAME
        )
        if (jsonString != null){
            return jsonString
        }
        return ""
    }



    fun fromCountriesJson(context: Context): ArrayList<CountriesModel> {
        return jsonParser.fromJson<ArrayList<CountriesModel>>(
            json = jsonFileString(context),
            object : TypeToken<ArrayList<CountriesModel>>(){}.type
        ) ?: arrayListOf()
    }
}