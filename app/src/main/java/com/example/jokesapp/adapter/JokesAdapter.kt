package com.example.jokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.databinding.JokeItemBinding
import com.example.jokesapp.model.Value


class JokesAdapter(
    private val value: MutableList<Value> = mutableListOf(),

    ) : RecyclerView.Adapter<JokesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val view = JokeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(value[position])
    }

    override fun getItemCount(): Int = value.size

    fun setNewJokes(newJoke: List<Value>) {
        value.clear()
        value.addAll(newJoke)
        notifyDataSetChanged()
    }

    fun setMoreJokes(newJoke: List<Value>) {
        value.addAll(newJoke)
        notifyDataSetChanged()
    }

}

class JokesViewHolder(
    private val binding: JokeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(value: Value) {
        binding.jokeText.text = value.joke

    }

}