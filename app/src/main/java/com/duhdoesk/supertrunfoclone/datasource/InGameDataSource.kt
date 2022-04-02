package com.duhdoesk.supertrunfoclone.datasource

import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class InGameDataSource(private val jsonFileReader: JsonFileReader) {

    companion object {
        private const val DECKS_JSON_FILE_NAME = "decks.json"
    }

    fun loadDecks(): List<Baralho> {
        val rawJson = jsonFileReader.readJsonAsset(DECKS_JSON_FILE_NAME)
            ?: return emptyList()

        val jsonReader = Json { isLenient = true }

        return jsonReader.decodeFromString(rawJson)
    }
}