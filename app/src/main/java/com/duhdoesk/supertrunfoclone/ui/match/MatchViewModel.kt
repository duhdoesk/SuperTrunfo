package com.duhdoesk.supertrunfoclone.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duhdoesk.supertrunfoclone.datasource.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Card
import com.duhdoesk.supertrunfoclone.ui.match.inGameHelper.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(private val deckLocalDataSource: DeckLocalDataSource) : ViewModel() {

    private val _deck = MutableLiveData<Deck>()
    val deck: LiveData<Deck> get() = _deck

    private val _myCards = MutableLiveData<List<Card>>()
    val myCards: LiveData<List<Card>> get() = _myCards
    val mCard: Card get() = _myCards.value!![0]

    private val _oppCards = MutableLiveData<List<Card>>()
    val oppCards: LiveData<List<Card>> get() = _oppCards
    val oCard: Card get() = _oppCards.value!![0]

    fun matchStart(index: Int) {
        getDeck(index)
        shuffleCards()
        dealCards()
    }

    fun cardBattle(att: String): Boolean {
        return when (att) {
            "radioOption1" -> {
                passingCard(mCard.att1 > oCard.att1)
                (mCard.att1 > oCard.att1)
            }
            "radioOption2" -> {
                passingCard(mCard.att2 > oCard.att2)
                (mCard.att2 > oCard.att2)
            }
            "radioOption3" -> {
                passingCard(mCard.att3 > oCard.att3)
                (mCard.att3 > oCard.att3)
            }
            "radioOption4" -> {
                passingCard(mCard.att4 > oCard.att4)
                (mCard.att4 > oCard.att4)
            }
            else -> {
                true
            }
        }
    }


    fun superTrunfoCall() : Boolean {
        val pattern = Regex("A")
        val winner: Boolean = !pattern.containsMatchIn(oCard.id)
        passingCard(winner)
        return winner
    }

    private fun getDeck(index: Int) {
        _deck.value = deckLocalDataSource.loadDecks()[index]
    }

    private fun shuffleCards() {
        _deck.value!!.cards = _deck.value!!.cards.shuffled()
    }

    private fun dealCards() {
        _myCards.value = _deck.value?.cards!!.subList(0, (_deck.value!!.cards.size + 1) / 2)
        _oppCards.value = _deck.value?.cards!!.subList((_deck.value!!.cards.size + 1) / 2, _deck.value!!.cards.size)
    }

    private fun passingCard(winner: Boolean) {
        val myList: MutableList<Card> = _myCards.value?.toMutableList()!!
        val oppList: MutableList<Card> = _oppCards.value?.toMutableList()!!

        when (winner) {
            true -> {
                myList.add(myList[0])
                myList.add(oppList[0])
            }
            else -> {
                oppList.add(oppList[0])
                oppList.add(myList[0])
            }
        }

        myList.removeAt(0)
        oppList.removeAt(0)

        _oppCards.postValue(oppList)
        _myCards.postValue(myList)
    }
}
