package com.example.jokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.jokesapp.R
import com.example.jokesapp.databinding.FragmentMainBinding
import com.example.jokesapp.model.Value
import com.example.jokesapp.utils.JokesState
import com.example.jokesapp.viewmodel.JokesViewModel


class MainFragment : BaseFragment() {
    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    // var jok: Value? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        jokesViewModel.getRandomJoke()
        jokesViewModel.allJokes.observe(viewLifecycleOwner) { state ->
            when (state) {
                is JokesState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is JokesState.SUCCESS<*> -> {

                    var jok: Value = state.joke as Value
                    binding.btnRandomJoke.setOnClickListener {
                        jokesViewModel.getRandomJoke()
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.apply {
                            setIcon(R.drawable.ic_baseline_tag_faces_24)
                            setTitle("JOKE")
                            setMessage(jok.joke)
                            setPositiveButton("HAHAHA") { _, _ ->
                            }
                        }.create().show()
                    }
                }
                is JokesState.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        state.error.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        binding.btnCustomJoke.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_characterJokeFragment)
        }

        binding.btnInfiniteJokes.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_neverEndingListFragment)
        }


        return binding.root

    }


}