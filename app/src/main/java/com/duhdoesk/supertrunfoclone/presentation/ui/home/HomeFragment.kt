package com.duhdoesk.supertrunfoclone.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.presentation.ui.theme.SuperTrunfoTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SetHomeFragment()
            }
        }
    }
    
    @Preview
    @Composable
    private fun SetHomeFragment() {
        SuperTrunfoTheme() {
            Surface(
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.title_logo),
                        contentDescription = "Logo do Super Trunfo Grow",
                        modifier = Modifier
                            .size(200.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.home_fragment_description),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp, 32.dp)
                            .fillMaxWidth()
                    )

                    Button(
                        onClick = {
                                  findNavController().navigate(R.id.action_destination_title_to_destination_collection)
                        },
                        modifier = Modifier
                            .padding(16.dp, 64.dp)
                            .size(160.dp, 40.dp),
                    ) {
                        Text(text = stringResource(id = R.string.play_button).uppercase())
                    }
                }
            }
        }
    }
}