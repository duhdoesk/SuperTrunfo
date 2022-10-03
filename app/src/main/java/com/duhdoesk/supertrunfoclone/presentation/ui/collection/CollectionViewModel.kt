package com.duhdoesk.supertrunfoclone.presentation.ui.collection

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duhdoesk.supertrunfoclone.datasource.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val localDataSource: DeckLocalDataSource) :
    ViewModel() {

    val decks: MutableState<List<Deck>> = mutableStateOf(ArrayList())

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            decks.value = localDataSource.loadDecks()
        }
    }

}