/*
 *
 *   Created Wasim Shaikh on 12/7/23, 2:25 AM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *   Last modified: 12/7/23, 2:24 AM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.example.tictactoeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _player1 = MutableLiveData<String>("")
    val player1: MutableLiveData<String> = _player1

    private val _player2 = MutableLiveData<String>("")
    val player2: MutableLiveData<String> = _player2

    private val _player1Score = MutableLiveData<Int>(0)
    val player1Score: MutableLiveData<Int> = _player1Score

    private val _player2Score = MutableLiveData<Int>(0)
    val player2Score: MutableLiveData<Int> = _player2Score

    private val _player1Turn = MutableLiveData<Boolean>(true)
    val player1Turn: MutableLiveData<Boolean> = _player1Turn

    private val _gameOver = MutableLiveData<Boolean>(false)
    val gameOver: MutableLiveData<Boolean> = _gameOver

    fun setPlayer1(name: String) {
        _player1.value = name
    }

    fun setPlayer2(name: String) {
        _player2.value = name
    }

    fun setPlayer1Score(score: Int) {
        _player1Score.value = score
    }

    fun setPlayer2Score(score: Int) {
        _player2Score.value = score
    }

    fun setPlayer1Turn(turn: Boolean) {
        _player1Turn.value = turn
    }

    fun setGameOver(over: Boolean) {
        _gameOver.value = over
    }

    fun reset() {
        _player1.value = ""
        _player2.value = ""
        _player1Score.value = 0
        _player2Score.value = 0
        _player1Turn.value = true
        _gameOver.value = false
    }

    fun clear() = viewModelScope.launch {
        onCleared()
    }
}