package com.duhdoesk.supertrunfoclone.Model

import android.content.Context
import android.util.Log
import com.duhdoesk.supertrunfoclone.ViewModel.InGameViewModel
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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