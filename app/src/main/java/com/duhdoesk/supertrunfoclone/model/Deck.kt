package com.duhdoesk.supertrunfoclone.model

import kotlinx.serialization.Serializable

@Serializable
data class Deck(
    val id: String = "",
    val name: String = "",
    val img: String? = null,
    val attributes: List<DeckAttribute> = emptyList(),
    var cards: List<Card> = emptyList()
)