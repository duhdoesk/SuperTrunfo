package com.duhdoesk.supertrunfoclone.presentation.ui.match

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.model.Card
import com.duhdoesk.supertrunfoclone.presentation.ui.theme.*
import com.duhdoesk.supertrunfoclone.util.loadPicture
import dagger.hilt.android.AndroidEntryPoint
import com.duhdoesk.supertrunfoclone.presentation.ui.match.MatchViewModel.Option.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class MatchFragment : Fragment() {

    private val viewModel: MatchViewModel by viewModels()
    private val args: MatchFragmentArgs by navArgs()
    private lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.matchStart(args.collection)

        (activity as AppCompatActivity).supportActionBar?.title =
            "Super Trunfo ${viewModel.deck!!.name}"

        when (viewModel.deck!!.id) {
            "1" -> R.raw.deck_1
            "2" -> R.raw.deck_2
            else -> null
        }?.let { sfxPlayer(it) }

        return ComposeView(requireContext()).apply {

            lifecycleScope.launch {

                viewModel.state.collect { state ->
                    when (state) {
                        is MatchState.Won -> {
                            findNavController().navigate(R.id.action_destination_inGame_to_destination_gameWon)
                        }
                        is MatchState.Lost -> {
                            findNavController().navigate(R.id.action_destination_inGame_to_destination_gameOver)
                        }
                        is MatchState.NextCard -> {
                            setContent {
                                SuperTrunfoTheme {
                                    Surface {
                                        SetMatchFragment(state.myCard)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    fun SetMatchFragment(card: Card) {

        val radioOptions = listOf(A, B, C, D)
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        val deck = viewModel.deck!!

        Column(modifier = Modifier
            .fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)) {
                card.img.let { url ->
                    val image = loadPicture(url = url, defaultImage = R.drawable.default_placeholder).value
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            contentDescription = "Deck image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {

                Text(
                    text = card.name,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                )

                Text(
                    text = card.id,
                    color = Yellow,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp))

            radioOptions.forEach { option ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                        .height(40.dp)
                        .background(if ((option == B) || (option == D)) MediumGrey else DarkGrey)
                        .selectable(
                            selected = (option == selectedOption),
                            onClick = {
                                onOptionSelected(option)
                                viewModel.selectedOption = option
                            },
                            role = Role.RadioButton
                        )
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Yellow
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(
                        text = deck.attributes.find { it.id == option.toString() }!!.label,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(start = 8.dp)
                    )

                    Text(
                        text = if (card.attributes.find { it.id == option.toString() }!!.value == 99999.0) {
                            "Turbina"
                        } else {
                            "${card.attributes.find { it.id == option.toString() }!!.value} ${deck.attributes.find { it.id == option.toString() }!!.unit}"
                        },
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))

            Row() {
                Text(
                    text = stringResource(id = R.string.your_cards),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 8.dp)
                )

                Text(
                    text = stringResource(id = R.string.opponent_cards),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }

            Row(Modifier.padding(top = 4.dp)) {
                Text(
                    text = viewModel.cardCounts.first.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(horizontal = 8.dp)
                )

                Text(
                    text = viewModel.cardCounts.second.toString(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (card.joker) Arrangement.SpaceAround else Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                if (card.joker) {
                    Button(onClick = {
                        val result = viewModel.superTrunfoCall()
                        view?.let {
                            if (result.first) {
                                Snackbar
                                    .make(it, "GGWP :) Card oponente: id ${result.second}", Snackbar.LENGTH_SHORT)
                                    .show()
                            } else {
                                Snackbar
                                    .make(it, "Bad News :( Card oponente: id ${result.second}", Snackbar.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }) {
                        Text(stringResource(id = R.string.super_trunfo))
                    }
                }
                
                Button(onClick = {
                    if (viewModel.selectedOption == null) viewModel.selectedOption = selectedOption
                    viewModel.cardBattle()
                }) { Text(stringResource(id = R.string.call_button)) }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun sfxPlayer(raw: Int) {
        mediaPlayer = MediaPlayer.create(context, raw)
        mediaPlayer.start()
    }
}