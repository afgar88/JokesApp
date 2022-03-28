package com.example.jokesapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokesapp.viewmodel.JokesViewModel

open class BaseFragment : Fragment() {

    protected val jokesViewModel: JokesViewModel by viewModels()
}