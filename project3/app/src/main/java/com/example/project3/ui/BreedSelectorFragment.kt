package com.example.project3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.project3.databinding.FragmentBreedSelectorBinding
import com.example.project3.viewmodel.DogViewModel

class BreedSelectorFragment : Fragment() {
    //Shared ViewModel
    private val viewModel: DogViewModel by activityViewModels()
    private var _binding: FragmentBreedSelectorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        Log.d("BreedSelector", "ViewModel Hash: ${viewModel.hashCode()}")

        //Observe the breedsList LiveData
        viewModel.breedsList.observe(viewLifecycleOwner) { breeds ->
            //Once an actual list of breeds is observed, show them in Spinner
            Log.d("BreedSelector", "Observed ${breeds.size} breeds")
            binding.tvTitle.text = "Select a Breed"

            //Get a list of breed names for the Spinner
            val breedNames = breeds.map { it.name }
            //Create dapter for Spinner
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                breedNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Apply adapter
            binding.spBreeds.adapter = adapter

            //Set selection for orientation changes
            binding.spBreeds.setSelection(breeds.indexOfFirst { it.name == viewModel.selectedBreed.value?.name })
        }
        //Handle user selecting a breed from spinner
        binding.spBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val breeds = viewModel.breedsList.value
                if (!breeds.isNullOrEmpty() && position < breeds.size) {
                    //Update selectedBreed in viewModel
                    viewModel.onBreedSelected(breeds[position])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}