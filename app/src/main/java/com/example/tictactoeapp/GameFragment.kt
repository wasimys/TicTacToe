/*
 *
 *   Created Wasim Shaikh on 12/7/23, 2:23 AM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *   Last modified: 12/7/23, 2:10 AM
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

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import com.example.tictactoeapp.databinding.GameFragmentBinding

class GameFragment : Fragment() , OnClickListener{

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var boardList = mutableListOf<Button>()


    private val shareViewModel: GameViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)

        for (buttons in boardList){
            buttons.setOnClickListener(this)
        }

        setTurnLabel()
    }

    private fun checkForVictory(s: String): Boolean {

        // Horizontal Victory
        if(match(binding.a1, s) && match(binding.a2,s) && match(binding.a3, s))
            return true

        if(match(binding.b1, s) && match(binding.b2,s) && match(binding.b3, s))
            return true

        if(match(binding.c1, s) && match(binding.c2,s) && match(binding.c3, s))
            return true

        // Vertical Victory
        if(match(binding.a1, s) && match(binding.b1,s) && match(binding.c1, s))
            return true

        if(match(binding.a2, s) && match(binding.b2,s) && match(binding.c2, s))
            return true

        if(match(binding.a3, s) && match(binding.b3,s) && match(binding.c3, s))
            return true

        // Diagonal Victory
        if(match(binding.a1, s) && match(binding.b2,s) && match(binding.c3, s))
            return true

        if(match(binding.a3, s) && match(binding.b2,s) && match(binding.c1, s))
            return true

        return false
    }

    private fun match(button: Button, symbol : String ): Boolean = button.text == symbol && button.text != ""


    private fun result(title: String){

        activity?.let {
            AlertDialog.Builder(it!!)
                .setTitle(title)
                .setPositiveButton("Reset") { _, _ ->
                    resetBoard()
                }
                .setCancelable(false)
                .show()
        }

        binding.player1Wins.text = "${shareViewModel.player1.value} Wins: ${shareViewModel.player1Score.value}"
        binding.player2Wins.text = "${shareViewModel.player2.value} Wins: ${shareViewModel.player2Score.value}"
    }

    private fun resetBoard() {
        for (buttons in boardList){
            buttons.text = ""
        }

        if(firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if(firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {

        for(button in boardList){
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(view: Button) {
        if(view.text != "")
            return

        if(currentTurn == Turn.NOUGHT)
        {
            view.text = NOUGHT
            currentTurn = Turn.CROSS
        } else if(currentTurn == Turn.CROSS) {

            view.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if(currentTurn == Turn.CROSS)
            turnText = "${shareViewModel.player1.value}'s Turn $CROSS "
        else if (currentTurn == Turn.NOUGHT)
            turnText = "${shareViewModel.player2.value}'s Turn $NOUGHT "


        binding.turnTV.text = turnText
    }

    override fun onClick(view: View?) {
        if(view !is Button)
            return
        addToBoard(view)

        if(checkForVictory(NOUGHT)) {
            shareViewModel.player2Score.value = shareViewModel.player2Score.value?.plus(1)
            result("${shareViewModel.player2.value} Win!")
        }else if(checkForVictory(CROSS)) {
            shareViewModel.player1Score.value = shareViewModel.player1Score.value?.plus(1)
            result("${shareViewModel.player1.value} Win!")
        }else if(fullBoard()){
            result("Draw")
        }
    }

    companion object
    {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}