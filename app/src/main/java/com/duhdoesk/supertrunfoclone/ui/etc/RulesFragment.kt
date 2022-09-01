package com.duhdoesk.supertrunfoclone.ui.etc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duhdoesk.supertrunfoclone.R

class RulesFragment : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }
}