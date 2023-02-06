package com.example.prac_android.step3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.prac_android.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding : FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = FragmentGameOverBinding.inflate(inflater,container,false)
        binding.tryAgainButton.setOnClickListener { view: View ->
            view.findNavController().navigate(com.example.prac_android.GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
        return binding.root
    }
}