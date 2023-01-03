package com.duhdoesk.supertrunfoclone.model

import kotlinx.serialization.Serializable

@Serializable
class DeckAttribute(
    val id: String = "",
    val label: String = "",
    val unit: String = ""
) {
}