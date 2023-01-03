package com.duhdoesk.supertrunfoclone.data.collection

import android.util.Log
import com.duhdoesk.supertrunfoclone.model.Deck
import com.duhdoesk.supertrunfoclone.util.JsonFileReader
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DeckRemoteDataSource @Inject constructor() {

    val database = Firebase.database
    val reference = database.reference
    var deckList: List<Deck> = mutableListOf()

    suspend fun loadDecks(): List<Deck> {

        reference
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deckList = it
                        .result
                        .children
                        .mapNotNull { doc ->
                            doc.getValue(Deck::class.java)
                    }

                    Log.d(
                        "debugging firebase (load decks remote)",
                        "data loaded from remote data source")
                } else {
                    Log.d(
                        "debugging firebase",
                        "loading error: ${it.exception?.message.toString()}"
                    )
                }
            }.await()

        return deckList
    }
}