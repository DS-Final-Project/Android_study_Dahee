package com.example.prac_android.step5

import androidx.lifecycle.ViewModel
import timber.log.Timber

class ScoreViewModel(finalScore: Int) : ViewModel() {
    var score = finalScore
    init {
        Timber.tag("ScoreViewModel").i("Final score is $finalScore")
    }
}