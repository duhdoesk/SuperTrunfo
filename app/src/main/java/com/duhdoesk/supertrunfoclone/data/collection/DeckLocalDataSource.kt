package com.duhdoesk.supertrunfoclone.datasource

import com.duhdoesk.supertrunfoclone.ui.match.inGameHelper.Deck
import com.duhdoesk.supertrunfoclone.util.JsonFileReader
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DeckLocalDataSource @Inject constructor(private val jsonFileReader: JsonFileReader) {

    companion object {
        private const val DECKS_JSON_FILE_NAME = "decks.json"
    }

    fun loadDecks(): List<Deck> {
        val rawJson = jsonFileReader.readJsonAsset(DECKS_JSON_FILE_NAME)
            ?: return emptyList()

        val jsonReader = Json { isLenient = true }

        return jsonReader.decodeFromString(rawJson)
    }
}