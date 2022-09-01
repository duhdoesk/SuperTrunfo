package com.duhdoesk.supertrunfoclone.util

import android.content.Context
import android.util.Log

class JsonFileReader(private val context: Context) {

    fun readJsonAsset(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (exception: Exception) {
            Log.e(JsonFileReader::class.simpleName, exception.message, exception)
            null
        }
    }

}