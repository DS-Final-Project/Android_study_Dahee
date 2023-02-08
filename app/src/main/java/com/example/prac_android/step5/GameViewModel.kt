package com.example.prac_android.step5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    // The current word
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
    get() = _word

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    init {
        resetList()
        nextWord()
        Timber.tag("GameViewModel").i("GameViewModel created!")

        _word.value = ""
        _score.value = 0
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }
    /** Methods for buttons presses **/

    //데이터 처리를 위한 코드 포함
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    //데이터 처리를 위한 코드 포함
    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Timber.tag("GameViewModel").i("GameViewModel destroyed!")
    }
}