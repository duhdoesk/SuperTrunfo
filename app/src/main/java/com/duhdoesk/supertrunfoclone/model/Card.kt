package com.duhdoesk.supertrunfoclone.model

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: String = "",
    val name: String = "",
    val img: String = "",
    val attributes: List<CardAttribute> = emptyList(),
    val joker: Boolean = false
)