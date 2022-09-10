package com.duhdoesk.supertrunfoclone.ui.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.databinding.FragmentMatchBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
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
        return binding.apply {
            vm.matchStart(args.collection.toInt())
            vm.myCards.observe(viewLifecycleOwner) {
                when {
                    vm.myCards.value!!.isEmpty() ->
                        findNavController().navigate(MatchFragmentDirections.actionDestinationInGameToDestinationGameOver())
                    vm.oppCards.value!!.isEmpty() ->
                        findNavController().navigate(MatchFragmentDirections.actionDestinationInGameToDestinationGameWon())
                    else ->
                        setViews()
                }
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Super Trunfo ${vm.deck.value?.name}"

        binding.buttonCall.setOnClickListener(View.OnClickListener {
            if (binding.radioGroup.checkedRadioButtonId == -1) {
                Snackbar.make(view, "Por favor, escolha uma opção para chamar.", Snackbar.LENGTH_SHORT).show()
            } else {
                if (vm.cardBattle(resources.getResourceEntryName(binding.radioGroup.checkedRadioButtonId))) {
                    Snackbar.make(view, "Você venceu! Boa escolha :)", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, "Você perdeu essa :(", Snackbar.LENGTH_SHORT).show()
                }
            }
        })

        binding.btSuperTrunfo.setOnClickListener(View.OnClickListener {
            val cardId: String = vm.oCard.id
            val result = when (vm.superTrunfoCall()) {
                true -> "Você venceu :)"
                else -> "Você perdeu o Super Trunfo :("
            }
            Snackbar.make(view, "Id do card rival: $cardId. $result", Snackbar.LENGTH_SHORT).show()
        })
    }

    private fun setViews() {
        binding.radioOption1.text = vm.deck.value!!.att1Label
        binding.radioOption2.text = vm.deck.value!!.att2Label
        binding.radioOption3.text = vm.deck.value!!.att3Label
        binding.radioOption4.text = vm.deck.value!!.att4Label

        binding.ivCardArt.load(vm.mCard.img) {
            listener(onError = { _: ImageRequest, _: ErrorResult ->
                binding.ivCardArt.setImageResource(R.drawable.ic_launcher_background)
            })
        }

        binding.tvCardId.text = vm.mCard.id
        binding.tvCardName.text = vm.mCard.name

        binding.tvOption1.text = "${vm.mCard.att1} ${vm.deck.value!!.att1Unit}"
        binding.tvOption2.text = "${vm.mCard.att2} ${vm.deck.value!!.att2Unit}"
        binding.tvOption3.text = "${vm.mCard.att3} ${vm.deck.value!!.att3Unit}"
        binding.tvOption4.text = "${vm.mCard.att4} ${vm.deck.value!!.att4Unit}"

        binding.tvYourCardsNumber.text = vm.myCards.value!!.size.toString()
        binding.tvOppCardsNumber.text = vm.oppCards.value!!.size.toString()

        binding.radioGroup.clearCheck()

        if (vm.mCard.trunfo)
            binding.btSuperTrunfo.visibility = View.VISIBLE else binding.btSuperTrunfo.visibility = View.GONE
    }
}
