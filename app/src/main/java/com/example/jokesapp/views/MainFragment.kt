package com.example.jokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jokesapp.R
import com.example.jokesapp.databinding.FragmentMainBinding
import com.example.jokesapp.model.Value
import com.example.jokesapp.utils.JokesState


class MainFragment : BaseFragment() {
    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }
    var jok: Value? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        jokesViewModel.allJokes.observe(viewLifecycleOwner) { state ->
            when (state) {
                is JokesState.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
                is JokesState.SUCCESS<*> -> {

                    jok = state.joke as Value


                }
                is JokesState.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        state.error.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            jokesViewModel.getRandomJoke()
            binding.btnRandomJoke.setOnClickListener {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.apply {
                    setIcon(R.drawable.ic_baseline_tag_faces_24)
                    setTitle("JOKE")
                    setMessage(jok!!.joke)
                    setPositiveButton("HAHAHA") { _, _ ->
                    }
                }.create().show()

            }

        }


        return binding.root

    }


}