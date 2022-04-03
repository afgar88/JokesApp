package com.example.jokesapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.databinding.FragmentNeverEndingListBinding
import com.example.jokesapp.model.JokesList
import com.example.jokesapp.model.Value
import com.example.jokesapp.utils.JokesState


class NeverEndingListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentNeverEndingListBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val explicit = jokesViewModel.explicit

        binding.infiniteJokes.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = jokesAdapter
        }
        jokesViewModel.getJokes(explicit)
        Log.d("Jokes", jokesViewModel.getJokes(explicit).toString())
        jokesViewModel.allJokes.observe(viewLifecycleOwner) { state ->
            when (state) {
                is JokesState.LOADING -> {
                    Toast.makeText(requireContext(), "loading...", Toast.LENGTH_LONG).show()
                }
                is JokesState.SUCCESS<*> -> {
                    Log.d("DATA_ENTER", state.toString())
                    when (state.joke) {
                        is List<*> -> {
                            jokesAdapter.setNewJokes(state.joke as List<Value>)
                        }
                    }

                    binding.infiniteJokes.addOnScrollListener(object :
                        RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            if (!binding.infiniteJokes.canScrollVertically(1)) {
                                Log.d("EndList", "hahahaha")
                                binding.infiniteJokes.adapter = jokesAdapter
                            }
                        }
                    })
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
}