package com.example.prac_android.step5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(finalScore: Int) : ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    private var _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
    get() = _eventPlayAgain

    init {
        Timber.tag("ScoreViewModel").i("Final score is $finalScore")
        _score.value = finalScore
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }
    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }
}