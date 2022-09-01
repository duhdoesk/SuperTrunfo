package com.duhdoesk.supertrunfoclone.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    lateinit var btPlay : Button

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        btPlay = binding.buttonPlayNow

        btPlay.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_destination_title_to_destination_collection
            )
        )

        return binding.root

        //Inflate the layout for this fragment
        /*val view = inflater.inflate(R.layout.fragment_title, container, false)
        buttonPlay = view.findViewById(R.id.buttonPlayNow)
        buttonPlay.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_destination_title_to_destination_collection, null))
        return view*/
    }

}