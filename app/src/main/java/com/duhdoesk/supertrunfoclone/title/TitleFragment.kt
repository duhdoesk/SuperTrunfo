package com.duhdoesk.supertrunfoclone.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.duhdoesk.supertrunfoclone.R

class TitleFragment : Fragment() {
    lateinit var buttonPlay : Button

    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_title, container, false)
        buttonPlay = view.findViewById(R.id.buttonPlayNow)
        buttonPlay.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_destination_title_to_destination_collection, null))
        return view
    }

}