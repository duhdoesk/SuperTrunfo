package com.duhdoesk.supertrunfoclone.datasource

import android.widget.Toast
import com.duhdoesk.supertrunfoclone.inGame.InGameFragment
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Carta
import kotlin.random.Random

class Datasource {

    companion object {

        fun getDeck(index: Int): Baralho {

            val baralhoCarrosIrados = Baralho(
                nome = "Carros Irados",
                op1Text = "Cilindradas",
                op2Text = "Velocidade Máxima",
                op3Text = "Potência",
                op4Text = "Peso",
                op1Unit = "",
                op2Unit = "km/h",
                op3Unit = "cv",
                op4Unit = "kg",
                cartas = mutableListOf(
                    Carta(
                        cardId = "A1",
                        nome = "Peugeot 307 Presence Pack",
                        op1 = 3000,
                        op2 = 245,
                        op3 = 210,
                        op4 = 1245
                    ),
                    Carta(
                        cardId = "A2",
                        nome = "Citroen C4 Cactus",
                        op1 = 2875,
                        op2 = 280,
                        op3 = 223,
                        op4 = 1020
                    ),
                    Carta(
                        cardId = "A3",
                        nome = "Marea Turbo",
                        op1 = 1244,
                        op2 = 195,
                        op3 = 180,
                        op4 = 1124
                    ),
                    Carta(
                        cardId = "A4",
                        nome = "Uno Escadinha",
                        op1 = 1600,
                        op2 = 500,
                        op3 = 200,
                        op4 = 800
                    )
                )
            )

            val baralho = when (index) {
                0 -> baralhoCarrosIrados
                else -> baralhoCarrosIrados
            }

            baralho.cartas = baralho.cartas.shuffled()
            return baralho
        }

        fun splitCards(baralho: Baralho, player: String): List<Carta>? {

            val totalCartas: Int = baralho.cartas.size

            val deck = when (player) {
                "me" -> {
                    baralho.cartas.subList(0, (totalCartas+1) / 2)
                }
                "opp" -> {
                    baralho.cartas.subList((totalCartas + 1) / 2, totalCartas)
                }
                else -> null
            }

            return deck
        }

        fun cardBattle(myCardValue: Int, oppCardValue: Int): String {
            if (myCardValue > oppCardValue) {
                return "W"
            } else {
                return "L"
            }
        }
    }
}