package com.example.prac_android.step5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.prac_android.R
import com.example.prac_android.databinding.FragmentTitle2Binding

class TitleFragment2 : Fragment() {

    private lateinit var binding : FragmentTitle2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_title2,container,false)

        binding.playGameButton.setOnClickListener {
            //findNavController().navigate(TitleFragment2Directions.actionTitleToGame())
        }

        return binding.root
    }

}