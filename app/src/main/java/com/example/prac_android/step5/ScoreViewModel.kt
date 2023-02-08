package com.example.prac_android.step5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(finalScore: Int) : ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score
    init {
        Timber.tag("ScoreViewModel").i("Final score is $finalScore")
        _score.value = finalScore
    }
}