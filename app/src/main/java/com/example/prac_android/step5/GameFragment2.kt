package com.example.prac_android.step5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prac_android.R.layout
import com.example.prac_android.databinding.FragmentGame2Binding
import timber.log.Timber

class GameFragment2 : Fragment() {

    private lateinit var binding: FragmentGame2Binding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layout.fragment_game2, container, false)
        Timber.tag("GameFragment").i("Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener {
            findNavController().navigate(GameFragment2Directions.actionGameToTitle())
        }

        binding.score = viewModel.score
        updateScoreText()
        updateWordText()
        return binding.root
    }

    /** Methods for buttons presses **/

    private fun onSkip() {
        viewModel.onSkip()
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        updateScoreText()
        updateWordText()
    }


    /** Methods for updating the UI **/

    private fun updateWordText() {
        binding.wordText.text = viewModel.word
    }

    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }
}