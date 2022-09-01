package com.duhdoesk.supertrunfoclone.ui.ending

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.duhdoesk.supertrunfoclone.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate the layout for this fragment
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.apply {
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btLetsDoIt: Button = binding.btLetsDoIt
        val btNotNow: Button = binding.btNotNow

        btLetsDoIt.setOnClickListener(View.OnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionDestinationGameOverToDestinationCollection())
        })

        btNotNow.setOnClickListener(View.OnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionDestinationGameOverToDestinationTitle())
        })
    }
}