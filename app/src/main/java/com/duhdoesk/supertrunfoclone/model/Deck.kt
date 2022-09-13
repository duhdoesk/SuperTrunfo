package com.duhdoesk.supertrunfoclone.model

import kotlinx.serialization.Serializable

@Serializable
data class Deck(
    val id: String,
    val name: String,
    val img: String,
    val attributes: List<DeckAttribute>,
    var cards: List<Card>
)