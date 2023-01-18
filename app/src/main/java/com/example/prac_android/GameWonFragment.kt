package com.example.prac_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prac_android.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    private lateinit var binding : FragmentGameWonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = FragmentGameWonBinding.inflate(inflater,container,false)
        return binding.root
    }
}