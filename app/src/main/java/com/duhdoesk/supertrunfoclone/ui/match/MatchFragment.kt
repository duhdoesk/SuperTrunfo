package com.duhdoesk.supertrunfoclone.ui.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.duhdoesk.supertrunfoclone.ui.match.MatchViewModel.Option.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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
                R.id.radioOption1 -> vm.selectedOption = A
                R.id.radioOption2 -> vm.selectedOption = B
                R.id.radioOption3 -> vm.selectedOption = C
                R.id.radioOption4 -> vm.selectedOption = D
            }
        }

        vm.deck.observe(viewLifecycleOwner) { deck ->
            val deckAtt = deck.attributes
            binding.radioOption1.text = deckAtt.find { it.id == "A" }!!.label
            binding.radioOption2.text = deckAtt.find { it.id == "B" }!!.label
            binding.radioOption3.text = deckAtt.find { it.id == "C" }!!.label
            binding.radioOption4.text = deckAtt.find { it.id == "D" }!!.label

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

        val myAtt = state.myCard.attributes
        val deckAtt = vm.deck.value!!.attributes
        binding.tvOption1.text = "${myAtt.find { it.id == "A" }!!.value} ${deckAtt.find { it.id == "A" }!!.unit}"
        binding.tvOption2.text = "${myAtt.find { it.id == "B" }!!.value} ${deckAtt.find { it.id == "B" }!!.unit}"
        binding.tvOption3.text = "${myAtt.find { it.id == "C" }!!.value} ${deckAtt.find { it.id == "C" }!!.unit}"
        binding.tvOption4.text = "${myAtt.find { it.id == "D" }!!.value} ${deckAtt.find { it.id == "D" }!!.unit}"

        binding.radioGroup.clearCheck()

        binding.btSuperTrunfo.isVisible = state.myCard.joker
    }
}