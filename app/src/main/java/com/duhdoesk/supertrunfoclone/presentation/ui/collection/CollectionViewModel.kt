package com.duhdoesk.supertrunfoclone.presentation.ui.collection

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duhdoesk.supertrunfoclone.data.collection.DeckRemoteDataSource
import com.duhdoesk.supertrunfoclone.data.collection.DeckLocalDataSource
import com.duhdoesk.supertrunfoclone.model.Deck
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val localDataSource: DeckLocalDataSource,
    private val remoteDataSource: DeckRemoteDataSource
    ) : ViewModel() {

    val decks: MutableState<List<Deck>> = mutableStateOf(ArrayList())

    init {
        newSearch()
    }

    private fun newSearch() {
        viewModelScope.launch {
            decks.value = remoteDataSource.loadDecks()
            if (decks.value == mutableListOf(null)) {
                decks.value = localDataSource.loadDecks()
                Log.d("debugging firebase", "loaded from local datasource")
            } else {
                Log.d("debugging firebase", "loaded from remote datasource")
            }
        }
    }

}