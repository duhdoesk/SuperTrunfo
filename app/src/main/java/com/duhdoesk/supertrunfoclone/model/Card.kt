package com.duhdoesk.supertrunfoclone.model

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: String,
    val img: String,
    val name: String,
    val att1: Int,
    val att2: Int,
    val att3: Int,
    val att4: Double,
    val trunfo: Boolean = false
)