package com.duhdoesk.supertrunfoclone.inGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.duhdoesk.supertrunfoclone.collection.collections.CarrosIrados
import com.duhdoesk.supertrunfoclone.databinding.FragmentInGameBinding

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding get() = _binding!!

    private val args: InGameFragmentArgs by navArgs()
    private var collection: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInGameBinding.inflate(inflater, container, false)
        return binding.apply {

            collection = args.collection
            binding.tvYourCards.text = "Your Cards: $collection"

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameStart()
    }

    private fun gameStart() {

        val baralho = when(collection) {
            "0" -> {
                CarrosIrados
            }
            "1" -> {
                CarrosIrados
            }
            else -> {
                CarrosIrados
            }
        }

        val carta = baralho.a1
        binding.tvCardName.text = carta.cardName
        binding.radioOption1.text = carta.opt1.toString()
        binding.radioOption2.text = carta.opt2.toString()
        binding.radioOption3.text = carta.opt3.toString()
        binding.radioOption4.text = carta.opt4.toString()
    }
}