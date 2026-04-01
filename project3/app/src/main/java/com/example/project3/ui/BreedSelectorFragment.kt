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
    // -Francisco: Shared ViewModel
    private val viewModel: DogViewModel by activityViewModels()
    // - Francisco: binding allows access to UI elements from XML
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
        // -Francisco: Observe the breedsList LiveData
        viewModel.breedsList.observe(viewLifecycleOwner) { breeds ->
            Log.d("BreedSelector", "Observed ${breeds.size} breeds")
            binding.tvTitle.text = "Select a Breed"

            val breedNames = breeds.map { it.name }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                breedNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spBreeds.adapter = adapter

            //Set selection for orientation changes
            binding.spBreeds.setSelection(breeds.indexOfFirst { it.name == viewModel.selectedBreed.value?.name })
        }
        // Francisco: Handle user selecting a breed from spinner
        binding.spBreeds.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val breeds = viewModel.breedsList.value
                if (!breeds.isNullOrEmpty() && position < breeds.size) {
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