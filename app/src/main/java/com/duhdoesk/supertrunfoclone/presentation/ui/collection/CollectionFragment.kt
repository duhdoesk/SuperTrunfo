package com.duhdoesk.supertrunfoclone.presentation.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.model.Deck
import com.duhdoesk.supertrunfoclone.presentation.components.DeckCard
import com.duhdoesk.supertrunfoclone.presentation.ui.theme.SuperTrunfoTheme
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {

    private val viewModel: CollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val decks = viewModel.decks.value
                SetCollectionFragment(decks)
            }
        }
    }

    @Composable
    fun SetCollectionFragment(decks: List<Deck>) {
        SuperTrunfoTheme() {
            Surface {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(id = R.string.collection_pick),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp, end = 8.dp, top = 16.dp
                            )
                    )

                    if (decks.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = stringResource(id = R.string.waiting_connection),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 8.dp, end = 8.dp, top = 8.dp
                                    )
                            )
                        }
                    } else {
                        LazyColumn(modifier = Modifier.padding(8.dp)) {
                            itemsIndexed(items = decks) { _, deck ->
                                DeckCard(
                                    deck = deck,
                                    onClick = {
                                        findNavController().navigate(
                                            CollectionFragmentDirections.actionDestinationCollectionToDestinationInGame(
                                                deck.id
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
