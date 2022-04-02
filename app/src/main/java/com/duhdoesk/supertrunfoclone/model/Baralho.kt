package com.duhdoesk.supertrunfoclone.inGame.inGameHelper

import android.graphics.drawable.Drawable
import kotlinx.serialization.Serializable

class DeckCollection(
    var decks: List<Baralho>
)

@Serializable
data class Baralho(
    val nome: String,
    val op1Text: String,
    val op2Text: String,
    val op3Text: String,
    val op4Text: String,
    val op1Unit: String,
    val op2Unit: String,
    val op3Unit: String,
    val op4Unit: String,
    val cover: String,
    var cartas: List<Carta>
)

@Serializable
class Carta(
    val cardId: String,
    val cardImg: String,
    val nome: String,
    val op1: Int,
    val op2: Int,
    val op3: Int,
    val op4: Double,
    val trunfo: Boolean = false
)