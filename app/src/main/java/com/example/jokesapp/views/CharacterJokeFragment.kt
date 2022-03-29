package com.example.jokesapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jokesapp.R
import com.example.jokesapp.databinding.FragmentCharacterJokeBinding


class CharacterJokeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentCharacterJokeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.btnCustomJoke.setOnClickListener{

        }

        return binding.root
    }


}