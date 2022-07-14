package com.example.dentrealitytestapp.data.util

import android.content.Context
import java.io.IOException

class Utils {
    object GetCountries{
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}