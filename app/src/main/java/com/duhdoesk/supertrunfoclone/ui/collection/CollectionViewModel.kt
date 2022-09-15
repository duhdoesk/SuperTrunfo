package com.duhdoesk.supertrunfoclone.ui.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duhdoesk.supertrunfoclone.datasource.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val localDataSource: DeckLocalDataSource) :
    ViewModel() {

    private val _decks = MutableLiveData<List<Deck>>()
    val decks: LiveData<List<Deck>>
        get() = _decks

    init {
        loadDecks()
    }

    private fun loadDecks() {
        _decks.value = localDataSource.loadDecks()
    }

}