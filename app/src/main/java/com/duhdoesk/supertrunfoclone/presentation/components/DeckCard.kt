package com.duhdoesk.supertrunfoclone.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duhdoesk.supertrunfoclone.model.Deck
import com.duhdoesk.supertrunfoclone.presentation.ui.theme.Yellow
import com.duhdoesk.supertrunfoclone.util.DEFAULT_IMAGE
import com.duhdoesk.supertrunfoclone.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterialApi::class)
@Composable
fun DeckCard(deck: Deck, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp,
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .wrapContentSize()
        ) {
            deck.img?.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Deck image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(400.dp, 120.dp)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Yellow)
                    .fillMaxWidth()
            ) {
                Text(
                    text = deck.name.uppercase(),
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .wrapContentSize()
                )
            }
        }
    }
}