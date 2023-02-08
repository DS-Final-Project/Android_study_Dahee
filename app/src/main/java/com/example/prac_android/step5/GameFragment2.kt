package com.example.prac_android.step5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.prac_android.R.layout
import com.example.prac_android.databinding.FragmentGame2Binding
import timber.log.Timber

class GameFragment2 : Fragment() {

    private lateinit var binding: FragmentGame2Binding

    //UI controller와 viewModel 연결
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layout.fragment_game2, container, false)
        Timber.tag("GameFragment").i("Called ViewModelProvider.get")
        //viewModel 초기화
        //아래 코드와 동일
        //viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }

        binding.score = viewModel.score
        updateScoreText()
        updateWordText()
        return binding.root
    }

    /** Methods for buttons presses **/

    //UI 업데이트를 위한 코드 포함
    private fun onSkip() {
        //viewModel에서 데이터 처리 후 UI controller에서 UI업데이트
        viewModel.onSkip()
        updateWordText()
        updateScoreText()
    }

    //UI 업데이트를 위한 코드 포함
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

    private fun onEndGame() {
        gameFinished()
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragment2Directions.actionGameToScore()
        action.score = viewModel.score
        NavHostFragment.findNavController(this).navigate(action)
    }

}