package com.duhdoesk.supertrunfoclone.inGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.duhdoesk.supertrunfoclone.databinding.FragmentInGameBinding

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding get() = _binding!!

    private val args: InGameFragmentArgs by navArgs()
    private var col: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInGameBinding.inflate(inflater, container, false)
        return binding.apply {

            col = args.collection
            binding.tvYourCards.text = "Your Cards: $col"

        }.root
    }
}