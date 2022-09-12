package com.duhdoesk.supertrunfoclone.ui.match.inGameHelper

import com.duhdoesk.supertrunfoclone.model.Card
import kotlinx.serialization.Serializable

@Serializable
data class Deck(
    val id: String,
    val name: String,
    val att1Label: String,
    val att2Label: String,
    val att3Label: String,
    val att4Label: String,
    val att1Unit: String,
    val att2Unit: String,
    val att3Unit: String,
    val att4Unit: String,
    val img: String,
    var cards: List<Card>
)