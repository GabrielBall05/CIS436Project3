package com.example.project3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project3.R
import com.example.project3.viewmodel.DogViewModel

class BreedDetailsFragment : Fragment() {

    //Gets the same DogViewModel instance owned by MainActivity
    //TODO: Have the image and textViews observe the viewModel's selectedBreed LiveData
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
        return inflater.inflate(R.layout.fragment_breed_details, container, false)
    }


}