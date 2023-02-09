package com.example.prac_android.step5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        viewModel.score.observe(viewLifecycleOwner, Observer{ newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.eventGameFinish.observe(viewLifecycleOwner,Observer<Boolean> { hasFinished ->
            if(hasFinished) gameFinished()
        })

        //GameViewModel에 data binding 추가
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.score = viewModel.score.value
        return binding.root
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragment2Directions.actionGameToScore()
        //data가 null이면 0을 반환하고 아니면 viewModel.score.value를 반환
        action.score = viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)

        viewModel.onGameFinishComplete()
    }

}