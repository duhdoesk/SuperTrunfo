package com.duhdoesk.supertrunfoclone.ui.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.databinding.FragmentMatchBinding
import com.duhdoesk.supertrunfoclone.model.Card
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MatchFragment : Fragment() {

    private val vm: MatchViewModel by viewModels()
    private val args: MatchFragmentArgs by navArgs()

    private lateinit var _binding: FragmentMatchBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.matchStart(args.collection)

        binding.buttonCall.setOnClickListener {
            if (vm.selectedOption == null) {
                Snackbar.make(
                    view,
                    "Por favor, escolha uma opção para chamar.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                if (vm.cardBattle()) {
                    Snackbar.make(view, "Você venceu! Boa escolha :)", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, "Você perdeu essa :(", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.btSuperTrunfo.setOnClickListener {
            val (winner, rivalCardId) = vm.superTrunfoCall()
            val result = when (winner) {
                true -> "Você venceu :)"
                else -> "Você perdeu o Super Trunfo :("
            }
            Snackbar.make(
                view,
                "Id do card rival: $rivalCardId. $result",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioOption1 -> vm.selectedOption = MatchViewModel.Option.A
                R.id.radioOption2 -> vm.selectedOption = MatchViewModel.Option.B
                R.id.radioOption3 -> vm.selectedOption = MatchViewModel.Option.C
                R.id.radioOption4 -> vm.selectedOption = MatchViewModel.Option.D
            }
        }

        vm.deck.observe(viewLifecycleOwner) { deck ->
            binding.radioOption1.text = deck.att1Label
            binding.radioOption2.text = deck.att2Label
            binding.radioOption3.text = deck.att3Label
            binding.radioOption4.text = deck.att4Label

            (activity as AppCompatActivity).supportActionBar?.title =
                "Super Trunfo ${deck.name}"
        }

        vm.matchState.observe(viewLifecycleOwner) {
            when (it) {
                MatchState.Lost -> {
                    findNavController().navigate(com.duhdoesk.supertrunfoclone.ui.match.MatchFragmentDirections.actionDestinationInGameToDestinationGameOver())
                }
                MatchState.Won -> {
                    findNavController().navigate(com.duhdoesk.supertrunfoclone.ui.match.MatchFragmentDirections.actionDestinationInGameToDestinationGameWon())
                }
                is MatchState.NextCard -> {
                    setCardInformation(it)
                }
            }
        }

        vm.cardCounts.observe(viewLifecycleOwner) { (myCards, oCards) ->
            binding.tvYourCardsNumber.text = myCards.toString()
            binding.tvOppCardsNumber.text = oCards.toString()
        }
    }

    private fun setCardInformation(state: MatchState.NextCard) {
        binding.ivCardArt.load(state.myCard.img) {
            listener(onError = { _: ImageRequest, _: ErrorResult ->
                binding.ivCardArt.setImageResource(R.drawable.ic_launcher_background)
            })
        }

        binding.tvCardId.text = state.myCard.id
        binding.tvCardName.text = state.myCard.name

        val deck = vm.deck.value
        binding.tvOption1.text = "${state.myCard.att1} ${deck?.att1Unit}"
        binding.tvOption2.text = "${state.myCard.att2} ${deck?.att2Unit}"
        binding.tvOption3.text = "${state.myCard.att3} ${deck?.att3Unit}"
        binding.tvOption4.text = "${state.myCard.att4} ${deck?.att4Unit}"

        binding.radioGroup.clearCheck()

        binding.btSuperTrunfo.isVisible = state.myCard.trunfo
    }
}