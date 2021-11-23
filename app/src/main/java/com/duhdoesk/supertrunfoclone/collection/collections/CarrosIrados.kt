package com.duhdoesk.supertrunfoclone.collection.collections

import com.duhdoesk.supertrunfoclone.R

data class CarrosIrados(
    val cardId: String,
    val cardName: String,
    val cover: Int,
    val opt1: Int,
    val opt2: Int,
    val opt3: Int,
    val opt4: Int,
    val trunfo: Boolean
    ) {

    companion object {
        val a1 = CarrosIrados(
            "A1",
            "Volkswagen Gol GTI",
            R.drawable.ic_launcher_foreground,
            1984,
            120,
            185,
            997,
            true
        )

        val a2 = CarrosIrados(
            "A2",
            "Chevrolet Kadett GSI",
            R.drawable.ic_launcher_foreground,
            1998,
            121,
            178,
            1100,
            false
        )

        val a3 = CarrosIrados(
            "A3",
            "Ford Escort XR3",
            R.drawable.ic_launcher_foreground,
            1984,
            116,
            187,
            1120,
            false
        )

        val a4 = CarrosIrados(
            "A4",
            "Fiat Uno Turbo",
            R.drawable.ic_launcher_foreground,
            1372,
            118,
            195,
            975,
            false
        )
        val a5 = CarrosIrados(
            "A5",
            "Fiat Uno Turbo",
            R.drawable.ic_launcher_foreground,
            1995,
            165,
            220,
            1275,
            false
        )

        val b1 = CarrosIrados(
            "B1",
            "Honda Civic VTi",
            R.drawable.ic_launcher_foreground,
            1595,
            160,
            215,
            1080,
            false
        )

        val b2 = CarrosIrados(
            "B2",
            "Audi RS2",
            R.drawable.ic_launcher_foreground,
            2226,
            315,
            262,
            1595,
            false
        )

        val b3 = CarrosIrados(
            "B3",
            "Mitsubishi Eclipse GST",
            R.drawable.ic_launcher_foreground,
            1997,
            213,
            224,
            1295,
            false
        )

        val b4 = CarrosIrados(
            "B4",
            "Citroen Xsara VTS",
            R.drawable.ic_launcher_foreground,
            1998,
            167,
            225,
            1190,
            false
        )

        val b5 = CarrosIrados(
            "B5",
            "Audi A3 Turbo",
            R.drawable.ic_launcher_foreground,
            1781,
            150,
            212,
            1180,
            false
        )
    }
}
