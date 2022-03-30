package com.example.jokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.databinding.JokeItemBinding
import com.example.jokesapp.model.Jokes
import com.example.jokesapp.model.Value
import com.example.jokesapp.model.ValueX


class JokesAdapter(
    private val value: MutableList<ValueX> = mutableListOf(),

    ) : RecyclerView.Adapter<JokesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val view = JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(value[position])
    }

    override fun getItemCount(): Int = value.size

    fun setNewJokes(newJoke: List<ValueX>) {
        value.clear()
        value.addAll(newJoke)
        notifyDataSetChanged()
    }

    fun setMoreJokes(newJoke: List<ValueX>) {
        value.addAll(newJoke)
        notifyDataSetChanged()
    }

}

class JokesViewHolder(
    private val binding: JokeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(value: ValueX) {
        binding.jokeText.text = value.joke

    }

}