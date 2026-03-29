package com.example.project3.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project3.R
import com.example.project3.viewmodel.DogViewModel
import kotlin.getValue

class BreedSelectorFragment : Fragment() {

    //Gets the same DogViewModel instance owned by MainActivity
    //TODO: Have the Spinner observe the viewModel's breedsList LiveData
    private val viewModel: DogViewModel by activityViewModels()

    //TODO: Binding variables and that stuff


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breed_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //This is what observing something from the viewModel looks like:
        viewModel.breedsList.observe(viewLifecycleOwner) { breeds ->
            //TODO: Code to populate spinner with the breed options. Binding will need to be set up to access the spinner
        }
    }

}