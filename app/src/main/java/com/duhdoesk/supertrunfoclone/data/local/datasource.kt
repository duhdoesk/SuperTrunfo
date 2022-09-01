package com.duhdoesk.supertrunfoclone.datasource

import android.content.Context
import com.duhdoesk.supertrunfoclone.ui.inGame.InGameDataSource
import com.duhdoesk.supertrunfoclone.ui.inGame.inGameHelper.Baralho
import com.duhdoesk.supertrunfoclone.ui.inGame.inGameHelper.Carta
import com.duhdoesk.supertrunfoclone.util.JsonFileReader

class Datasource {

    companion object {

        fun getListOfDecks(context: Context): List<Baralho> {
            val decks: List<Baralho> = InGameDataSource(JsonFileReader(context)).loadDecks()
            return decks
        }

        fun getDeck(context: Context, index: Int = 0): Baralho {
            val decks: List<Baralho> = InGameDataSource(JsonFileReader(context)).loadDecks()
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
}