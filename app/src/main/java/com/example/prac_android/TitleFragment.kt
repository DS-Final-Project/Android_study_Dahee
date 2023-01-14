package com.example.prac_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prac_android.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding : FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = FragmentTitleBinding.inflate(inflater,container,false)
        return binding.root
    }
}