package com.example.jokesapp.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jokesapp.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment : Fragment() {

    protected val jokesViewModel: JokesViewModel by sharedViewModel()
}