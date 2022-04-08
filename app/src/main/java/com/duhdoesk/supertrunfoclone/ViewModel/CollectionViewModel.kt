package com.duhdoesk.supertrunfoclone.ViewModel

import android.content.Context
import com.duhdoesk.supertrunfoclone.Model.JsonFileReader
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho

class CollectionViewModel {

    companion object {

        fun getListOfDecks(context: Context): List<Baralho> {
            val decks: List<Baralho> = InGameViewModel(JsonFileReader(context)).loadDecks()
            return decks
        }
    }
}