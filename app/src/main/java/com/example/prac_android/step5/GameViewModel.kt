package com.example.prac_android.step5

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    // The current word
    var word = ""

    // The current score
    var score = 0

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
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
    }
    /** Methods for buttons presses **/

    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }


    override fun onCleared() {
        super.onCleared()
        Timber.tag("GameViewModel").i("GameViewModel destroyed!")
    }
}