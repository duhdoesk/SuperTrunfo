package com.duhdoesk.supertrunfoclone.ViewModel

import android.content.Context
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Carta
import com.duhdoesk.supertrunfoclone.Model.JsonFileReader
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class InGameViewModel(private val jsonFileReader: JsonFileReader) {

    companion object {
        private const val DECKS_JSON_FILE_NAME = "decks.json"

        fun getDeck(context: Context, index: Int = 0): Baralho {
            val decks: List<Baralho> = InGameViewModel(JsonFileReader(context)).loadDecks()
            val deck: Baralho = decks[index]

            deck.cartas = deck.cartas.shuffled()
            return deck
        }

        fun splitCards(baralho: Baralho, player: String): List<Carta>? {

            val totalCartas: Int = baralho.cartas.size

            val deck = when (player) {
                "me" -> baralho.cartas.subList(0, (totalCartas + 1) / 2)
                "opp" -> baralho.cartas.subList((totalCartas + 1) / 2, totalCartas)
                else -> null
            }
            return deck
        }

        fun cardBattle(myCardValue: Int, oppCardValue: Int): String {
            return if (myCardValue > oppCardValue) "Player" else "CPU"
        }

        fun cardBattle(myCardValue: Double, oppCardValue: Double): String {
            return if (myCardValue > oppCardValue) "Player" else "CPU"
        }

        fun superTrunfo(cardId: String): Boolean {
            val pattern = Regex("A")
            return pattern.containsMatchIn(cardId)
        }
    }

    fun loadDecks(): List<Baralho> {
        val rawJson = jsonFileReader.readJsonAsset(DECKS_JSON_FILE_NAME)
            ?: return emptyList()

        val jsonReader = Json { isLenient = true }

        return jsonReader.decodeFromString(rawJson)
    }
}