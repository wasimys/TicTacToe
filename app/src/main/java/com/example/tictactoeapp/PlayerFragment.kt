/*
 *
 *   Created Wasim Shaikh on 12/7/23, 2:24 AM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *   Last modified: 12/7/23, 1:51 AM
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
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.tictactoeapp.databinding.PlayersFragmentBinding

class PlayerFragment : Fragment() {

    private var _binding: PlayersFragmentBinding? = null
    private val binding get() = _binding!!

    private val shareViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayersFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {

            val player1Name = binding.editTextPlayer1.text.toString()
            val player2Name = binding.editTextPlayer2.text.toString()

            shareViewModel.setPlayer1(player1Name.ifEmpty { "Player1" })
            shareViewModel.setPlayer2(player2Name.ifEmpty { "Player2" })

            Navigation.findNavController(view).navigate(R.id.action_playersFragment_to_gameFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}