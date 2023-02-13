package com.example.prac_android.step5

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {

    companion object {
        //Time when the game is over
        private const val DONE = 0L

        //Countdown time interval(시간 간격)
        private const val ONE_SECOND = 1000L

        //Total time for the game
        private const val COUNTDOWN_TIME = 6000L
    }

    // The current word
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
    get() = _word

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    //Countdown time
    private var _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
    get() = _currentTime

    //The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get() = _eventGameFinish

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

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }
        timer.start()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
           resetList()
        } else {
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

    //게임 종료 이벤트
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    override fun onCleared() {
        super.onCleared()
        //메모리 누수 방지를 위해 timer 취소
        timer.cancel()
        //Timber.tag("GameViewModel").i("GameViewModel destroyed!")
    }
}