package com.duhdoesk.supertrunfoclone.inGame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.duhdoesk.supertrunfoclone.databinding.FragmentInGameBinding
import com.duhdoesk.supertrunfoclone.datasource.Datasource
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding get() = _binding!!

    private val args: InGameFragmentArgs by navArgs()
    private var col: Int? = null
    private var baralho: Baralho? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInGameBinding.inflate(inflater, container, false)
        return binding.apply {

            col = args.collection.toInt()
            baralho = Datasource.getDeck(col!!)

            val myCards = Datasource.splitCards(baralho!!, "me")
            val opponentCards = Datasource.splitCards(baralho!!, "opp")

            val myDeckSize = myCards?.size
            val oppDeckSize = opponentCards?.size

            binding.tvYourCards.text = "Your Cards: $myDeckSize"
            binding.tvOpponentCards.text = "Opponent Cards: $oppDeckSize"

            val myCard = myCards?.get(0)
            binding.tvCardName.text = myCard?.nome
            binding.tvCardId.text = myCard?.cardId
            binding.radioOption1.text = "${baralho?.op1Text}: ${myCard?.op1.toString()} ${baralho?.op1Unit}"
            binding.radioOption2.text = "${baralho?.op2Text}: ${myCard?.op2.toString()} ${baralho?.op2Unit}"
            binding.radioOption3.text = "${baralho?.op3Text}: ${myCard?.op3.toString()} ${baralho?.op3Unit}"
            binding.radioOption4.text = "${baralho?.op4Text}: ${myCard?.op4.toString()} ${baralho?.op4Unit}"

        }.root
    }
}