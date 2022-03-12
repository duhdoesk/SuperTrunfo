package com.duhdoesk.supertrunfoclone.inGame.inGameHelper

class Baralho(
    val nome: String,
    val op1Text: String,
    val op2Text: String,
    val op3Text: String,
    val op4Text: String,
    val op1Unit: String,
    val op2Unit: String,
    val op3Unit: String,
    val op4Unit: String,
    var cartas: List<Carta>
)

class Carta(
    val cardId: String,
    val nome: String,
    val op1: Int,
    val op2: Int,
    val op3: Int,
    val op4: Int
)