package com.example.jokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jokesapp.R
import com.example.jokesapp.databinding.FragmentCharacterJokeBinding
import com.example.jokesapp.model.Value
import com.example.jokesapp.utils.JokesState


class CharacterJokeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentCharacterJokeBinding.inflate(layoutInflater)
    }

    var firstName: String? = null
    var lastName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

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

                    val jok = state.joke as Value
                    binding.btnCustomJoke.setOnClickListener {
                        if (getName()) {
                            jokesViewModel.getCustomJoke(firstName!!, lastName!!)
                            val alertDialog = AlertDialog.Builder(context)
                            alertDialog.apply {
                                setIcon(R.drawable.ic_baseline_tag_faces_24)
                                setTitle("JOKE")
                                setMessage(jok.joke)
                                setPositiveButton("HAHAHA") { _, _ ->
                                }
                            }.create().show()
                        } else
                            Toast.makeText(
                                context,
                                "Error white First Name -space- Last Name",
                                Toast.LENGTH_LONG
                            ).show()
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

        return binding.root
    }

    private fun getName(): Boolean {
        if (binding.editCustomJoke.text.isNotEmpty()) {
            val name = binding.editCustomJoke.text.split("\\p{Space}".toRegex()).toTypedArray()
            if (name.size == 2) {
                firstName = name[0]
                lastName = name[1]
                return true
            }
            return false
        }
        return false
    }

}