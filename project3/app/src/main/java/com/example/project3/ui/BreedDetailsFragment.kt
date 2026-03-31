package com.example.project3.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.project3.databinding.FragmentBreedDetailsBinding
import com.example.project3.viewmodel.DogViewModel

class BreedDetailsFragment : Fragment() {
    // -Francisco: shared viewModel between fragments
    private val viewModel: DogViewModel by activityViewModels()
    // -Francisco: binding allows access to UI elements
    private var _binding: FragmentBreedDetailsBinding? = null
    private val binding get() = _binding!!

    // -Francisco: onCreateView is called when the fragment is first created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    // -Francisco: onViewCreated is called when the fragment's view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Force a test update
        binding.tvName.text = "Waiting for data..."
        binding.tvTemperament.text = "Waiting for data..."
        binding.tvOrigin.text = "Waiting for data..."
        binding.tvLifeSpan.text = "Waiting for data..."

        viewModel.selectedBreed.observe(viewLifecycleOwner) { breed ->
            Log.d("BreedDetails", "Updating UI for breed: ${breed.name}")
            
            binding.tvName.text = breed.name
            binding.tvTemperament.text = breed.temperament
            binding.tvOrigin.text = breed.origin
            binding.tvLifeSpan.text = "${breed.lifeSpan} years"

            if (!breed.imageUrl.isNullOrEmpty()) {
                binding.imgDogImage.load(breed.imageUrl) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.stat_notify_error)
                }
            } else {
                binding.imgDogImage.setImageDrawable(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}