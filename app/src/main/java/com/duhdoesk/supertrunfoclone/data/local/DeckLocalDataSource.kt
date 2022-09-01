package com.duhdoesk.supertrunfoclone.datasource

import android.content.Context
import com.duhdoesk.supertrunfoclone.model.Card
import com.duhdoesk.supertrunfoclone.ui.inGame.InGameDataSource
import com.duhdoesk.supertrunfoclone.ui.inGame.inGameHelper.Deck
import com.duhdoesk.supertrunfoclone.util.JsonFileReader

class Datasource {

    companion object {

        fun getListOfDecks(context: Context): List<Deck> {
            val decks: List<Deck> = InGameDataSource(JsonFileReader(context)).loadDecks()
            return decks
        }

        fun getDeck(context: Context, index: Int = 0): Deck {
            val decks: List<Deck> = InGameDataSource(JsonFileReader(context)).loadDecks()
            val deck: Deck = decks[index]

            deck.cards = deck.cards.shuffled()
            return deck
        }

        fun splitCards(deck: Deck, player: String): List<Card>? {

            val totalCartas: Int = deck.cards.size

            val deck = when (player) {
                "me" -> deck.cards.subList(0, (totalCartas + 1) / 2)
                "opp" -> deck.cards.subList((totalCartas + 1) / 2, totalCartas)
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