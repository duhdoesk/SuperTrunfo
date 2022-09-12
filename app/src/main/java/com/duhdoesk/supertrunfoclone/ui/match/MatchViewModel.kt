package com.duhdoesk.supertrunfoclone.ui.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duhdoesk.supertrunfoclone.datasource.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Card
import com.duhdoesk.supertrunfoclone.ui.match.inGameHelper.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class MatchState {
    object Lost : MatchState()
    object Won : MatchState()
    data class NextCard(val myCard: Card, val oCard: Card) : MatchState()
}

@HiltViewModel
class MatchViewModel @Inject constructor(private val deckLocalDataSource: DeckLocalDataSource) : ViewModel() {

    private var myCards: MutableList<Card> = mutableListOf()
    private var oppCards: MutableList<Card> = mutableListOf()

    val deck = MutableLiveData<Deck>()
    var cardCounts = MutableLiveData<Pair<Int, Int>>()
    val matchState = MutableLiveData<MatchState>()

    enum class Option {
        A, B, C, D
    }

    var selectedOption: Option? = null

    fun matchStart(deckId: String) {
        deck.value = getDeckAndShuffle(deckId)
        dealCards()
    }

    fun cardBattle(): Boolean {
        val myCard = (matchState.value as? MatchState.NextCard)?.myCard ?: return false
        val oCard = (matchState.value as? MatchState.NextCard)?.oCard ?: return false
        return when (selectedOption) {
            Option.A -> {
                passingCard(myCard.att1 > oCard.att1)
                (myCard.att1 > oCard.att1)
            }
            Option.B -> {
                passingCard(myCard.att2 > oCard.att2)
                (myCard.att2 > oCard.att2)
            }
            Option.C -> {
                passingCard(myCard.att3 > oCard.att3)
                (myCard.att3 > oCard.att3)
            }
            Option.D -> {
                passingCard(myCard.att4 > oCard.att4)
                (myCard.att4 > oCard.att4)
            }
            else -> false
        }.also {
            selectedOption = null
        }
    }

    fun superTrunfoCall(): Pair<Boolean, String> {
        val state = matchState.value as? MatchState.NextCard ?: return false to ""
        val pattern = Regex("A")
        val winner: Boolean = !pattern.containsMatchIn(state.oCard.id)

        val oCardId = state.oCard.id

        passingCard(winner)
        return winner to oCardId
    }

    private fun getDeckAndShuffle(deckId: String): Deck? {
        val deck =  deckLocalDataSource.loadDecks().find { it.id == deckId } ?: return null
        return deck.copy(cards = deck.cards.shuffled())
    }


    private fun dealCards() {
        deck.value?.let {
            myCards = it.cards.subList(0, (it.cards.size + 1) / 2).toMutableList()
            oppCards = it.cards.subList((it.cards.size + 1) / 2, it.cards.size).toMutableList()
            matchState.value = MatchState.NextCard(myCards[0], oppCards[0])
            cardCounts.value = myCards.size to oppCards.size
        }
    }

    private fun passingCard(winner: Boolean) {
        when (winner) {
            true -> {
                myCards.add(myCards[0])
                myCards.add(oppCards[0])
            }
            else -> {
                oppCards.add(oppCards[0])
                oppCards.add(myCards[0])
            }
        }

        myCards.removeAt(0)
        oppCards.removeAt(0)

        cardCounts.value = myCards.size to oppCards.size

        if (myCards.isEmpty()) {
            matchState.value = MatchState.Lost
        } else if (oppCards.isEmpty()) {
            matchState.value = MatchState.Won
        } else {
            matchState.value = MatchState.NextCard(myCards[0], oppCards[0])
        }
    }
}
