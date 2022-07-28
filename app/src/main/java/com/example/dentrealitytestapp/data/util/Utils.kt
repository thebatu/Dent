package com.example.dentrealitytestapp.data.util

import android.content.Context
import com.example.dentrealitytestapp.Constants
import java.io.IOException
import java.text.DecimalFormat

class Utils {
    object GetCountries {
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
    object FormatNumber {
        fun formatNum(num: Double?): String {
            val df = DecimalFormat("#######,###")
            return "${df.format(num)} ${Constants.KM}"
        }
    }
}