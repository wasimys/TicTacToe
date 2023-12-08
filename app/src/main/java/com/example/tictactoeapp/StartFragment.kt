/*
 *
 *   Created Wasim Shaikh on 12/7/23, 2:22 AM
 *   Copyright Ⓒ 2023. All rights reserved Ⓒ 2023
 *   Last modified: 12/7/23, 2:14 AM
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 *   except in compliance with the License. You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENS... Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.example.tictactoeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class StartFragment : Fragment() {

    private val shareViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startGameButton = view.findViewById<Button>(R.id.playButton)
        startGameButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_startFragment_to_playersFragment)
            shareViewModel.reset()
            shareViewModel.setGameOver(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        shareViewModel.clear()
    }
}